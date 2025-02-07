package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Unit tests for {@link SystemPropertiesRepository}
 *
 * @author jschalanda
 */
public class SystemPropertiesRepositoryTest {

    @Test
    public void testRead() {
        final TestSystem testSystem = new TestSystem();
        testSystem.putProperty("java.version", "1.8");
        final Repository repository = new SystemPropertiesRepository("", testSystem);

        Assert.assertNull(repository.read("This system property should not exist"));
        Assert.assertEquals("1.8", repository.read("java.version"));
    }

    @Test
    public void testReadWithPrefix() {
        final TestSystem testSystem = new TestSystem();
        testSystem.putProperty("java.version", "1.8");
        final Repository testRepository = new SystemPropertiesRepository("java.", testSystem);
        Assert.assertEquals("1.8", testRepository.read("version"));
    }

    @Test
    public void testReadNames() {
        final TestSystem testSystem = new TestSystem();
        testSystem.putProperty("graylog2.opensearch.node.name", "my-node");
        testSystem.putProperty("graylog2.opensearch.node.roles", "search");
        testSystem.putProperty("java_home", "/usr/share/java");
        final Repository testRepository = new SystemPropertiesRepository("graylog2.", testSystem);

        final Collection<String> names = testRepository.readNames("opensearch.");
        Assert.assertEquals(2, names.size());
        Assert.assertTrue(names.contains("opensearch.node.name"));
        Assert.assertTrue(names.contains("opensearch.node.roles"));

        Assert.assertEquals("my-node", testRepository.read("opensearch.node.name"));
        Assert.assertEquals("search", testRepository.read("opensearch.node.roles"));
    }

}