package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.repositories.PropertiesRepository;
import com.github.joschi.jadconfig.testbeans.SimpleConfigurationBean;
import org.junit.Assert;
import org.junit.Test;

public class AggregatedPropertiesTest {

    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

    @Test
    public void testPrefixProperties() throws ValidationException, RepositoryException {
        final PropertiesRepository repository = new PropertiesRepository(PROPERTIES_FILE);

        final SimpleConfigurationBean configurationBean = new SimpleConfigurationBean();
        final JadConfig jadConfig = new JadConfig(repository, configurationBean);
        jadConfig.process();

        Assert.assertNotNull(configurationBean.getOpensearchProperties());
        Assert.assertEquals(4, configurationBean.getOpensearchProperties().size());

        Assert.assertEquals("search,cluster_manager", configurationBean.getOpensearchProperties().get("node.roles"));
        Assert.assertEquals("10gb", configurationBean.getOpensearchProperties().get("node.search.cache.size"));
        Assert.assertEquals("/tmp", configurationBean.getOpensearchProperties().get("path.repo"));
        Assert.assertEquals("debug", configurationBean.getOpensearchProperties().get("logger.reindex.level"));
    }
}
