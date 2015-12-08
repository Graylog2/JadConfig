package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.repositories.InMemoryRepository;
import com.github.joschi.jadconfig.repositories.PropertiesRepository;
import com.github.joschi.jadconfig.testbeans.ConverterFailureBean;
import com.github.joschi.jadconfig.testbeans.DefaultValueConfigurationBean;
import com.github.joschi.jadconfig.testbeans.EmptyBean;
import com.github.joschi.jadconfig.testbeans.FoobarConfigurationBean;
import com.github.joschi.jadconfig.testbeans.InheritedBeanSubClass;
import com.github.joschi.jadconfig.testbeans.InheritedBeanSubSubClass;
import com.github.joschi.jadconfig.testbeans.Multi1ConfigurationBean;
import com.github.joschi.jadconfig.testbeans.Multi2ConfigurationBean;
import com.github.joschi.jadconfig.testbeans.NonExistingParameterBean;
import com.github.joschi.jadconfig.testbeans.RequiredParameterBean;
import com.github.joschi.jadconfig.testbeans.SaveMeBean;
import com.github.joschi.jadconfig.testbeans.SimpleConfigurationBean;
import com.github.joschi.jadconfig.testbeans.TrimBean;
import com.github.joschi.jadconfig.testbeans.ValidatedConfigurationBean;
import com.github.joschi.jadconfig.testbeans.ValidatorMethodConfigurationBean;
import com.github.joschi.jadconfig.testbeans.VoidConfigurationBean;
import com.github.joschi.jadconfig.testconverters.FoobarConverterFactory;
import com.github.joschi.jadconfig.validators.StringNotBlankValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit tests for {@link JadConfig}
 *
 * @author jschalanda
 */
public class JadConfigTest {

    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private JadConfig jadConfig;
    private Repository repository;

    @Before
    public void setUp() {

        repository = new PropertiesRepository(PROPERTIES_FILE);
    }

    @Test
    public void testProcess() throws RepositoryException, ValidationException {

        SimpleConfigurationBean configurationBean = new SimpleConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();

        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
        Assert.assertEquals(123456, configurationBean.getMyInt());
        Assert.assertEquals(1234567890123L, configurationBean.getMyLong());
        Assert.assertEquals(5.432E-1f, configurationBean.getMyFloat(), 0.0f);
        Assert.assertEquals(1.23E45d, configurationBean.getMyDouble(), 0.0d);
        Assert.assertTrue(configurationBean.isMyBoolean());

        List<String> stringList = new ArrayList<String>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.add("four");
        stringList.add("five");

        Assert.assertEquals(stringList, configurationBean.getStringList());
        Assert.assertEquals(URI.create("http://example.com/"), configurationBean.getUri());
        Assert.assertEquals(new File("testConfiguration.properties"), configurationBean.getFile());
        Assert.assertEquals(Paths.get("testConfiguration.properties"), configurationBean.getPath());
    }

    @Test
    public void testProcessWithMultipleRepositories() throws RepositoryException, ValidationException {

        final SimpleConfigurationBean configurationBean = new SimpleConfigurationBean();
        final Repository inMemoryRepository = new InMemoryRepository(new HashMap<String, String>(1) {{
            put("test.string", "Override");
        }});

        jadConfig = new JadConfig(Arrays.asList(inMemoryRepository, repository), configurationBean);
        jadConfig.process();

        Assert.assertEquals("Override", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
        Assert.assertEquals(123456, configurationBean.getMyInt());
        Assert.assertEquals(1234567890123L, configurationBean.getMyLong());
        Assert.assertEquals(5.432E-1f, configurationBean.getMyFloat(), 0.0f);
        Assert.assertEquals(1.23E45d, configurationBean.getMyDouble(), 0.0d);
        Assert.assertTrue(configurationBean.isMyBoolean());

        List<String> stringList = new ArrayList<String>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.add("four");
        stringList.add("five");

        Assert.assertEquals(stringList, configurationBean.getStringList());
        Assert.assertEquals(URI.create("http://example.com/"), configurationBean.getUri());
        Assert.assertEquals(new File("testConfiguration.properties"), configurationBean.getFile());
    }

    @Test
    public void testProcessNonExistingProperty() throws RepositoryException, ValidationException {

        NonExistingParameterBean configurationBean = new NonExistingParameterBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertNull(configurationBean.getNonExisting());
    }

    @Test(expected = ParameterException.class)
    public void testProcessRequiredPropertyNotFound() throws RepositoryException, ValidationException {

        RequiredParameterBean configurationBean = new RequiredParameterBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
    }

    @Test
    public void testProcessDefaultProperty() throws RepositoryException, ValidationException {

        DefaultValueConfigurationBean configurationBean = new DefaultValueConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
    }

    @Test
    public void testDumpDefaultPropertiesWithoutProcess() throws RepositoryException, ValidationException {

        DefaultValueConfigurationBean configurationBean = new DefaultValueConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        final Map<String, String> configDump = jadConfig.dump();
        Assert.assertEquals("Will be overwritten", configDump.get("test.string"));
        Assert.assertEquals("123", configDump.get("test.does-not-exist"));
        Assert.assertEquals("0", configDump.get("test.short"));
    }

    @Test
    public void testProcessValidatorMethod() throws RepositoryException, ValidationException {
        ValidatorMethodConfigurationBean configurationBean = new ValidatorMethodConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();

        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
        Assert.assertEquals(123456, configurationBean.getMyInt());
        Assert.assertEquals(1234567890123L, configurationBean.getMyLong());
    }

    @Test
    public void testProcessValidatorMethodThrowsException() throws RepositoryException, ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("BOOM");

        Map<String, String> properties = Collections.singletonMap("test.string", "Not Test");
        ValidatorMethodConfigurationBean configurationBean = new ValidatorMethodConfigurationBean();
        jadConfig = new JadConfig(new InMemoryRepository(properties), configurationBean);


        jadConfig.process();
    }

    @Test
    public void testProcessEmptyBean() throws RepositoryException, ValidationException {

        EmptyBean configurationBean = new EmptyBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
    }

    @Test(expected = ParameterException.class)
    public void testProcessVoidBean() throws RepositoryException, ValidationException {

        VoidConfigurationBean configurationBean = new VoidConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
    }

    @Test
    public void testParameterTrimming() throws RepositoryException, ValidationException {

        TrimBean configurationBean = new TrimBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();

        Assert.assertEquals("Test", configurationBean.getTrimmedString());
        Assert.assertEquals("Test ", configurationBean.getUntrimmedString());
        Assert.assertEquals(123456, configurationBean.getMyInt());
    }

    @Test
    public void testProcessNoBean() throws RepositoryException, ValidationException {

        jadConfig = new JadConfig(repository);

        jadConfig.process();
    }

    @Test
    public void testDumpNoBean() throws RepositoryException, ValidationException {

        jadConfig = new JadConfig(repository);

        jadConfig.process();
        Assert.assertTrue(jadConfig.dump().isEmpty());
    }

    @Test
    public void testProcessMultipleBeans() throws RepositoryException, ValidationException {

        Multi1ConfigurationBean bean1 = new Multi1ConfigurationBean();
        Multi2ConfigurationBean bean2 = new Multi2ConfigurationBean();

        jadConfig = new JadConfig(repository, bean1, bean2);

        jadConfig.process();

        Assert.assertEquals("Test", bean1.getMyString());
        Assert.assertEquals(123, bean2.getMyByte());
    }

    @Test
    public void testAddConfigurationBean() throws RepositoryException, ValidationException {

        Multi1ConfigurationBean bean1 = new Multi1ConfigurationBean();
        Multi2ConfigurationBean bean2 = new Multi2ConfigurationBean();

        jadConfig = new JadConfig(repository);

        jadConfig.addConfigurationBean(bean1);
        jadConfig.addConfigurationBean(bean2);

        jadConfig.process();

        Assert.assertEquals("Test", bean1.getMyString());
        Assert.assertEquals(123, bean2.getMyByte());
    }

    @Test
    public void testDumpMultipleBeans() throws RepositoryException, ValidationException {

        Multi1ConfigurationBean bean1 = new Multi1ConfigurationBean();
        Multi2ConfigurationBean bean2 = new Multi2ConfigurationBean();

        jadConfig = new JadConfig(repository);

        jadConfig.addConfigurationBean(bean1);
        jadConfig.addConfigurationBean(bean2);

        jadConfig.process();
        final Map<String, String> configDump = jadConfig.dump();

        Assert.assertEquals("Test", configDump.get("test.string"));
        Assert.assertEquals("123", configDump.get("test.byte"));
    }

    @Test
    public void testProcessCustomConverter() throws RepositoryException, ValidationException {

        FoobarConfigurationBean configurationBean = new FoobarConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();

        Assert.assertEquals("Foobar", configurationBean.getMyString());
    }

    @Test
    public void testProcessCustomConverterFactory() throws RepositoryException, ValidationException {

        SimpleConfigurationBean configurationBean = new SimpleConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.addConverterFactory(new FoobarConverterFactory());
        jadConfig.process();

        Assert.assertEquals("Foobar", configurationBean.getMyString());
    }

    @Test
    public void testDumpEmptyBean() throws RepositoryException, ValidationException {

        Repository repository = new InMemoryRepository();
        EmptyBean configurationBean = new EmptyBean();
        jadConfig = new JadConfig(repository, configurationBean);
        jadConfig.process();

        final Map<String, String> configDump = jadConfig.dump();
        Assert.assertTrue(configDump.isEmpty());
    }

    @Test
    public void testDump() throws RepositoryException, ValidationException, URISyntaxException, IOException {

        Repository inMemoryRepository = new InMemoryRepository();

        SaveMeBean bean = new SaveMeBean();
        jadConfig = new JadConfig(inMemoryRepository, bean);
        jadConfig.process();

        File file = File.createTempFile("JadConfigTest-testDump", "");

        bean.setMyString("Test");
        bean.setMyInteger(123);
        bean.setMyUri(new URI("http://example.com/"));
        bean.setMyFile(file);

        final Map<String, String> configDump = jadConfig.dump();

        Assert.assertEquals("Test", configDump.get("save.me.string"));
        Assert.assertEquals("123", configDump.get("save.me.integer"));
        Assert.assertEquals("http://example.com/", configDump.get("save.me.uri"));
        Assert.assertEquals(file.getPath(), configDump.get("save.me.file"));
    }

    @Test
    public void testInheritedBean() throws Exception {
        InheritedBeanSubClass bean = new InheritedBeanSubClass();
        jadConfig = new JadConfig(repository, bean);
        jadConfig.process();

        Assert.assertEquals("Test", bean.getMyString());
        Assert.assertEquals(1234567890123L, bean.getMyInheritedLong());
    }

    @Test
    public void testDoubleInheritedBean() throws Exception {
        InheritedBeanSubSubClass bean = new InheritedBeanSubSubClass();
        jadConfig = new JadConfig(repository, bean);
        jadConfig.process();

        Assert.assertEquals("Test", bean.getMyString());
        Assert.assertEquals(1234567890123L, bean.getMyInheritedLong());
        Assert.assertEquals(new URI("http://example.com/"), bean.getMyUri());
    }

    @Test
    public void testDumpInheritedBean() throws Exception {
        InheritedBeanSubClass bean = new InheritedBeanSubClass();
        jadConfig = new JadConfig(repository, bean);
        jadConfig.process();

        final Map<String, String> configDump = jadConfig.dump();

        Assert.assertEquals(2, configDump.size());
        Assert.assertEquals("Test", configDump.get("test.string"));
        Assert.assertEquals("1234567890123", configDump.get("test.long"));
    }

    @Test
    public void testDumpDoubleInheritedBean() throws Exception {
        InheritedBeanSubSubClass bean = new InheritedBeanSubSubClass();
        jadConfig = new JadConfig(repository, bean);
        jadConfig.process();

        final Map<String, String> configDump = jadConfig.dump();

        Assert.assertEquals(3, configDump.size());
        Assert.assertEquals("Test", configDump.get("test.string"));
        Assert.assertEquals("1234567890123", configDump.get("test.long"));
        Assert.assertEquals("http://example.com/", configDump.get("test.uri"));
    }

    @Test
    public void testGetConverterFactories() throws ValidationException, RepositoryException {
        final FoobarConverterFactory converterFactory = new FoobarConverterFactory();
        jadConfig = new JadConfig();
        jadConfig.addConverterFactory(converterFactory);

        final List<ConverterFactory> converterFactories = jadConfig.getConverterFactories();
        Assert.assertEquals(2, converterFactories.size());
        Assert.assertTrue(converterFactories.contains(converterFactory));
    }

    @Test
    public void testGetConfigurationBeans() throws ValidationException, RepositoryException {
        final EmptyBean bean1 = new EmptyBean();
        final EmptyBean bean2 = new EmptyBean();
        jadConfig = new JadConfig(repository, bean1, bean2);

        final List<Object> beans = jadConfig.getConfigurationBeans();
        Assert.assertEquals(2, beans.size());
        Assert.assertTrue(beans.contains(bean1));
        Assert.assertTrue(beans.contains(bean2));
    }

    @Test
    public void testGetRepositories() throws ValidationException, RepositoryException {
        final InMemoryRepository repository = new InMemoryRepository();
        jadConfig = new JadConfig(repository);

        final List<Repository> repositories = jadConfig.getRepositories();
        Assert.assertEquals(1, repositories.size());
        Assert.assertTrue(repositories.contains(repository));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRepositoryThrowsExceptionWhenNull() {
        new JadConfig(repository).setRepository(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRepositoriesThrowsExceptionWhenArrayIsNull() {
        new JadConfig(repository).setRepositories((Repository[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRepositoriesThrowsExceptionWhenCollectionIsNull() {
        new JadConfig(repository).setRepositories((Collection<Repository>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRepositoriesThrowsExceptionWhenArrayIsEmpty() {
        new JadConfig(repository).setRepositories();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRepositoriesThrowsExceptionWhenCollectionIsEmpty() {
        new JadConfig(repository).setRepositories(Collections.<Repository>emptyList());
    }

    @Test
    public void testArgumentLessConstructor() {
        jadConfig = new JadConfig();

        Assert.assertTrue(jadConfig.getConfigurationBeans().isEmpty());
        Assert.assertTrue(jadConfig.getRepositories().isEmpty());
        Assert.assertEquals(1, jadConfig.getConverterFactories().size());
    }

    @Test
    public void testParameterExceptionContainsParameterName() throws RepositoryException, ValidationException {
        expectedException.expect(ParameterException.class);
        expectedException.expectMessage("Couldn't convert value for parameter \"test.string\"");

        ConverterFailureBean configurationBean = new ConverterFailureBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();
    }

    @Test
    public void testProcessValidatedBean() throws RepositoryException, ValidationException {
        ValidatedConfigurationBean configurationBean = new ValidatedConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        jadConfig.process();

        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
        Assert.assertEquals(12345, configurationBean.getMyInetPort());
        Assert.assertEquals(123456, configurationBean.getMyInt());
        Assert.assertEquals(1234567890123L, configurationBean.getMyLong());
    }

    @Test
    public void testProcessNullPropertiesDoNotOverwriteDefaultValues() throws RepositoryException, ValidationException {
        final SingleFieldBean bean = new SingleFieldBean();
        jadConfig = new JadConfig(new InMemoryRepository(Collections.singletonMap("field", (String) null)), bean);

        jadConfig.process();
    }

    public static class SingleFieldBean {
        @Parameter(value = "field", required = true, validators = StringNotBlankValidator.class)
        private String field = "test";

        public String getField() {
            return field;
        }
    }


}

