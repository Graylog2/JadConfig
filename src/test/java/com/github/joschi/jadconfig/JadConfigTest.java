package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.repositories.InMemoryRepository;
import com.github.joschi.jadconfig.repositories.PropertiesRepository;
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
import com.github.joschi.jadconfig.testbeans.ValidatorMethodConfigurationBean;
import com.github.joschi.jadconfig.testbeans.VoidConfigurationBean;
import com.github.joschi.jadconfig.testconverters.FoobarConverterFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Unit tests for {@link JadConfig}
 *
 * @author jschalanda
 */
public class JadConfigTest {

    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

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
    public void testSaveEmptyBean() throws RepositoryException, ValidationException {

        Repository repository = new InMemoryRepository();
        EmptyBean configurationBean = new EmptyBean();
        jadConfig = new JadConfig(repository, configurationBean);
        jadConfig.process();
        jadConfig.save();
    }

    @Test
    public void testSave() throws RepositoryException, ValidationException, URISyntaxException, IOException {

        Repository inMemoryRepository = new InMemoryRepository();

        SaveMeBean bean = new SaveMeBean();
        jadConfig = new JadConfig(inMemoryRepository, bean);
        jadConfig.process();

        File file = File.createTempFile("JadConfigTest-testSave", "");

        bean.setMyString("Test");
        bean.setMyInteger(123);
        bean.setMyUri(new URI("http://example.com/"));
        bean.setMyFile(file);

        jadConfig.save();

        SaveMeBean otherBean = new SaveMeBean();
        JadConfig otherJadConfig = new JadConfig(inMemoryRepository, otherBean);
        otherJadConfig.process();

        Assert.assertEquals("Test", otherBean.getMyString());
        Assert.assertEquals(Integer.valueOf(123), otherBean.getMyInteger());
        Assert.assertEquals(new URI("http://example.com/"), otherBean.getMyUri());
        Assert.assertEquals(file.getCanonicalPath(), otherBean.getMyFile().getCanonicalPath());
    }

    @Test
    public void testSaveMultipleRepositories() throws RepositoryException, ValidationException, URISyntaxException, IOException {

        Repository repo1 = new InMemoryRepository();
        Repository repo2 = new InMemoryRepository();

        SaveMeBean bean = new SaveMeBean();
        jadConfig = new JadConfig(Arrays.asList(repo1, repo2), bean);
        jadConfig.process();

        File file = File.createTempFile("JadConfigTest-testSave", "");

        bean.setMyString("Test");
        bean.setMyInteger(123);
        bean.setMyUri(new URI("http://example.com/"));
        bean.setMyFile(file);

        jadConfig.save();

        SaveMeBean repo1Bean = new SaveMeBean();
        JadConfig repo1Config = new JadConfig(repo1, repo1Bean);
        repo1Config.process();

        Assert.assertEquals("Test", repo1Bean.getMyString());
        Assert.assertEquals(Integer.valueOf(123), repo1Bean.getMyInteger());
        Assert.assertEquals(new URI("http://example.com/"), repo1Bean.getMyUri());
        Assert.assertEquals(file.getCanonicalPath(), repo1Bean.getMyFile().getCanonicalPath());

        SaveMeBean repo2Bean = new SaveMeBean();
        JadConfig repo2Config = new JadConfig(repo1, repo2Bean);
        repo2Config.process();

        Assert.assertEquals("Test", repo2Bean.getMyString());
        Assert.assertEquals(Integer.valueOf(123), repo2Bean.getMyInteger());
        Assert.assertEquals(new URI("http://example.com/"), repo2Bean.getMyUri());
        Assert.assertEquals(file.getCanonicalPath(), repo2Bean.getMyFile().getCanonicalPath());
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
    public void testSaveInheritedBean() throws Exception {
        Repository inMemoryRepository = new InMemoryRepository();
        InheritedBeanSubClass bean = new InheritedBeanSubClass();
        jadConfig = new JadConfig(inMemoryRepository, bean);
        jadConfig.process();

        bean.setMyString("Foobar!");
        bean.setMyInheritedLong(3210987654321L);

        jadConfig.save();

        InheritedBeanSubClass otherBean = new InheritedBeanSubClass();
        JadConfig otherJadConfig = new JadConfig(inMemoryRepository, otherBean);
        otherJadConfig.process();

        Assert.assertEquals("Foobar!", otherBean.getMyString());
        Assert.assertEquals(3210987654321L, otherBean.getMyInheritedLong());
    }

    @Test
    public void testSaveDoubleInheritedBean() throws Exception {
        Repository inMemoryRepository = new InMemoryRepository();
        InheritedBeanSubSubClass bean = new InheritedBeanSubSubClass();
        jadConfig = new JadConfig(inMemoryRepository, bean);
        jadConfig.process();

        bean.setMyString("Foobar!");
        bean.setMyInheritedLong(3210987654321L);
        bean.setMyUri(new URI("http://www.google.com"));

        jadConfig.save();

        InheritedBeanSubSubClass otherBean = new InheritedBeanSubSubClass();
        JadConfig otherJadConfig = new JadConfig(inMemoryRepository, otherBean);
        otherJadConfig.process();

        Assert.assertEquals("Foobar!", otherBean.getMyString());
        Assert.assertEquals(3210987654321L, otherBean.getMyInheritedLong());
        Assert.assertEquals(new URI("http://www.google.com"), otherBean.getMyUri());
    }
}
