package com.github.joschi.jadconfig.info;

import com.github.joschi.jadconfig.JadConfig;
import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;
import com.github.joschi.jadconfig.RestartRequirement;
import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.documentation.Documentation;
import com.github.joschi.jadconfig.repositories.EnvironmentRepository;
import com.github.joschi.jadconfig.repositories.InMemoryRepository;
import com.github.joschi.jadconfig.repositories.SystemPropertiesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ParameterInfoCollectorTest {

    public static class BeanA {
        @Documentation("The port to listen on")
        @Parameter(value = "port", requiresRestart = RestartRequirement.REQUIRED)
        private int port = 9000;

        @Parameter(value = "tags")
        private final List<String> tags = new ArrayList<>(Arrays.asList("a", "b"));

        @Parameter(value = "password", required = true, sensitive = true)
        private String password;

        @Parameter(value = "new_name", fallbackPropertyName = "old_name", requiresRestart = RestartRequirement.NOT_REQUIRED)
        private String renamed = "default";

        @Documentation(visible = false)
        @Parameter("shared")
        private String shared = "fromA";
    }

    public static class InheritingBean extends BeanA {
        @Parameter("extra")
        private String extra = "x";
    }

    public static class BeanB {
        @Parameter(value = "shared", requiresRestart = RestartRequirement.REQUIRED)
        private Integer shared = 42;
    }

    private static InMemoryRepository repository(String... keyValues) {
        final Map<String, String> properties = new HashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            properties.put(keyValues[i], keyValues[i + 1]);
        }
        return new InMemoryRepository(properties);
    }

    private static Map<String, ParameterInfo> process(List<Repository> repositories, Object... beans) throws RepositoryException, ValidationException {
        final ParameterInfoCollector collector = new ParameterInfoCollector();
        final JadConfig jadConfig = new JadConfig(repositories, beans).addParameterListener(collector);
        jadConfig.process();
        return collector.getParameterInfos();
    }

    @Test
    public void recordsSourceOfFirstRepositoryProvidingValue() throws Exception {
        final Repository first = repository("password", "secret");
        final Repository second = repository("port", "1234", "password", "other");

        final Map<String, ParameterInfo> infos = process(Arrays.asList(first, second), new BeanA());

        final ParameterInfo port = infos.get("port");
        Assertions.assertFalse(port.isDefault());
        Assertions.assertNotNull(port.source());
        Assertions.assertSame(second, port.source().repository());
        Assertions.assertEquals("port", port.source().propertyName());
        Assertions.assertEquals("InMemoryRepository", port.source().description());
        Assertions.assertEquals("1234", port.value());

        final ParameterInfo password = infos.get("password");
        Assertions.assertNotNull(password.source());
        Assertions.assertSame(first, password.source().repository());
    }

    @Test
    public void recordsDeclaration() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("port", "1234", "password", "secret")), new BeanA());

        final ParameterDeclaration port = infos.get("port").declarations().getFirst();
        Assertions.assertEquals(BeanA.class, port.beanClass());
        Assertions.assertEquals("port", port.metadata().fieldName());
        Assertions.assertEquals(int.class, port.metadata().type());
        Assertions.assertEquals(9000, port.defaultValue());
        Assertions.assertEquals("9000", port.defaultValueAsString());
        Assertions.assertFalse(port.metadata().required());
        Assertions.assertFalse(port.metadata().nullable());

        final ParameterDeclaration tags = infos.get("tags").declarations().getFirst();
        Assertions.assertEquals("java.util.List<java.lang.String>", tags.metadata().type().getTypeName());
        Assertions.assertEquals("[a, b]", tags.defaultValueAsString());
        Assertions.assertTrue(tags.metadata().nullable());

        final ParameterDeclaration password = infos.get("password").declarations().getFirst();
        Assertions.assertTrue(password.metadata().required());
        Assertions.assertFalse(password.metadata().nullable());
        Assertions.assertNull(password.defaultValue());
        Assertions.assertNull(password.defaultValueAsString());
    }

    @Test
    public void recordsParametersUsingDefaultValue() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret")), new BeanA());

        final ParameterInfo tags = infos.get("tags");
        Assertions.assertTrue(tags.isDefault());
        Assertions.assertNull(tags.source());
        Assertions.assertNull(tags.value());
    }

    @Test
    public void recordsFallbackPropertyName() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret", "old_name", "legacy")), new BeanA());

        final ParameterInfo renamed = infos.get("new_name");
        Assertions.assertNotNull(renamed.source());
        Assertions.assertEquals("old_name", renamed.source().propertyName());
        Assertions.assertEquals("legacy", renamed.value());
    }

    @Test
    public void hidesValueOfSensitiveParameters() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret")), new BeanA());

        final ParameterInfo password = infos.get("password");
        Assertions.assertTrue(password.isSensitive());
        Assertions.assertNull(password.value());
        Assertions.assertNotNull(password.source());
        Assertions.assertFalse(password.toString().contains("secret"));
    }

    @Test
    public void keepsDefaultValuesWhenProcessingRepeatedly() throws Exception {
        final ParameterInfoCollector collector = new ParameterInfoCollector();
        final BeanA beanA = new BeanA();
        final JadConfig jadConfig = new JadConfig(repository("port", "1234", "password", "secret"), beanA)
                .addParameterListener(collector);

        jadConfig.process();
        jadConfig.addConfigurationBean(new BeanB());
        jadConfig.process();

        final Map<String, ParameterInfo> infos = collector.getParameterInfos();
        Assertions.assertEquals(1234, beanA.port);
        Assertions.assertEquals(1, infos.get("port").declarations().size());
        Assertions.assertEquals(9000, infos.get("port").declarations().getFirst().defaultValue());
        Assertions.assertEquals(2, infos.get("shared").declarations().size());
    }

    @Test
    public void collectsAllDeclarationsOfParameter() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret", "shared", "7")), new BeanA(), new BeanB());

        final ParameterInfo shared = infos.get("shared");
        Assertions.assertEquals("7", shared.value());
        Assertions.assertEquals(2, shared.declarations().size());
        Assertions.assertEquals(BeanA.class, shared.declarations().get(0).beanClass());
        Assertions.assertEquals("fromA", shared.declarations().get(0).defaultValue());
        Assertions.assertEquals(BeanB.class, shared.declarations().get(1).beanClass());
        Assertions.assertEquals(42, shared.declarations().get(1).defaultValue());
        Assertions.assertEquals(Integer.class, shared.declarations().get(1).metadata().type());
    }

    @Test
    public void describesEnvironmentAndSystemPropertySources() throws Exception {
        final String property = "jadconfig.test.port";
        System.setProperty(property, "1234");
        try {
            final Map<String, ParameterInfo> infos = process(Arrays.asList(
                    new SystemPropertiesRepository("jadconfig.test."),
                    repository("password", "secret")), new BeanA());

            Assertions.assertEquals("system property jadconfig.test.port", Objects.requireNonNull(infos.get("port").source()).description());
        } finally {
            System.clearProperty(property);
        }

        Assertions.assertEquals("environment variable GRAYLOG_HTTP_BIND_ADDRESS",
                new EnvironmentRepository("GRAYLOG_").describeSource("http_bind_address"));
    }

    @Test
    public void recordsDocumentation() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret")), new BeanA());

        final ParameterMetadata port = infos.get("port").declarations().getFirst().metadata();
        Assertions.assertEquals("The port to listen on", port.documentation());
        Assertions.assertTrue(port.visible());

        final ParameterMetadata shared = infos.get("shared").declarations().getFirst().metadata();
        Assertions.assertNull(shared.documentation());
        Assertions.assertFalse(shared.visible());

        final ParameterMetadata tags = infos.get("tags").declarations().getFirst().metadata();
        Assertions.assertNull(tags.documentation());
        Assertions.assertTrue(tags.visible());
    }

    @Test
    public void recordsInheritedParametersWithBeanClass() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret")), new InheritingBean());

        Assertions.assertEquals(InheritingBean.class, infos.get("extra").declarations().getFirst().beanClass());
        Assertions.assertEquals(InheritingBean.class, infos.get("port").declarations().getFirst().beanClass());
        Assertions.assertEquals(9000, infos.get("port").declarations().getFirst().defaultValue());
    }

    @Test
    public void recordsRequiresRestart() throws Exception {
        final Map<String, ParameterInfo> infos = process(List.of(repository("password", "secret")), new BeanA(), new BeanB());

        Assertions.assertEquals(RestartRequirement.REQUIRED, infos.get("port").declarations().getFirst().metadata().requiresRestart());
        Assertions.assertEquals(RestartRequirement.REQUIRED, infos.get("port").requiresRestart());

        Assertions.assertEquals(RestartRequirement.NOT_REQUIRED, infos.get("new_name").requiresRestart());

        // Not annotated
        Assertions.assertEquals(RestartRequirement.UNKNOWN, infos.get("tags").declarations().getFirst().metadata().requiresRestart());
        Assertions.assertEquals(RestartRequirement.UNKNOWN, infos.get("tags").requiresRestart());

        // Required by one of the declarations, unknown for the other one
        final ParameterInfo shared = infos.get("shared");
        Assertions.assertEquals(RestartRequirement.UNKNOWN, shared.declarations().get(0).metadata().requiresRestart());
        Assertions.assertEquals(RestartRequirement.REQUIRED, shared.declarations().get(1).metadata().requiresRestart());
        Assertions.assertEquals(RestartRequirement.REQUIRED, shared.requiresRestart());
    }

    @Test
    public void combinesRestartRequirementsOfDeclarations() {
        Assertions.assertEquals(RestartRequirement.NOT_REQUIRED,
                info(RestartRequirement.NOT_REQUIRED, RestartRequirement.NOT_REQUIRED).requiresRestart());
        Assertions.assertEquals(RestartRequirement.UNKNOWN,
                info(RestartRequirement.NOT_REQUIRED, RestartRequirement.UNKNOWN).requiresRestart());
        Assertions.assertEquals(RestartRequirement.REQUIRED,
                info(RestartRequirement.NOT_REQUIRED, RestartRequirement.REQUIRED).requiresRestart());
        Assertions.assertEquals(RestartRequirement.UNKNOWN, info().requiresRestart());
    }

    private static ParameterInfo info(RestartRequirement... requirements) {
        final List<ParameterDeclaration> declarations = new ArrayList<>();
        for (int i = 0; i < requirements.length; i++) {
            final ParameterMetadata metadata = new ParameterMetadata("name", "field" + i, String.class,
                    false, true, false, requirements[i], null, true);
            declarations.add(new ParameterDeclaration(BeanA.class, metadata, null, null));
        }
        return new ParameterInfo("name", null, null, declarations);
    }
}
