package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.repositories.PropertiesRepository;
import com.github.joschi.jadconfig.testbeans.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
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
        jadConfig = new JadConfig(configurationBean, repository);

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
    public void testProcessNonExistingProperty() throws RepositoryException, ValidationException {

        NonExistingParameterBean configurationBean = new NonExistingParameterBean();
        jadConfig = new JadConfig(configurationBean, repository);

        jadConfig.process();
        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertNull(configurationBean.getNonExisting());
    }

    @Test(expected = ParameterException.class)
    public void testProcessRequiredPropertyNotFound() throws RepositoryException, ValidationException {

        RequiredParameterBean configurationBean = new RequiredParameterBean();
        jadConfig = new JadConfig(configurationBean, repository);

        jadConfig.process();
    }

    @Test
    public void testProcessDefaultProperty() throws RepositoryException, ValidationException {

        DefaultValueConfigurationBean configurationBean = new DefaultValueConfigurationBean();
        jadConfig = new JadConfig(configurationBean, repository);

        jadConfig.process();
        Assert.assertEquals("Test", configurationBean.getMyString());
        Assert.assertEquals(123, configurationBean.getMyByte());
        Assert.assertEquals(1234, configurationBean.getMyShort());
    }

    @Test
    public void testProcessValidatorMethod() throws RepositoryException, ValidationException {

        ValidatorMethodConfigurationBean configurationBean = new ValidatorMethodConfigurationBean();
        jadConfig = new JadConfig(configurationBean, repository);

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
        jadConfig = new JadConfig(configurationBean, repository);

        jadConfig.process();
    }

    @Test(expected = ParameterException.class)
    public void testProcessVoidBean() throws RepositoryException, ValidationException {

        VoidConfigurationBean configurationBean = new VoidConfigurationBean();
        jadConfig = new JadConfig(configurationBean, repository);

        jadConfig.process();
    }
}
