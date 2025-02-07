package com.github.joschi.jadconfig.repositories;

import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Unit tests for {@link EnvironmentRepository}
 *
 * @author jschalanda
 */
public class EnvironmentRepositoryTest {

    @Test
    public void testRead() throws RepositoryException {
        final TestSystem testEnv = new TestSystem();
        testEnv.putEnv("JAVA_HOME", "/usr/share/java");
        final Repository repository = new EnvironmentRepository("", true, testEnv);
        Assert.assertNull(repository.read("This environment variable should not exist"));
        Assert.assertEquals("/usr/share/java", repository.read("JAVA_HOME"));
    }

    @Test
    public void testUpperCaseEnabledRead() throws RepositoryException {
        final TestSystem testEnv = new TestSystem();
        testEnv.putEnv("JAVA_HOME", "/usr/share/java");
        final Repository repository = new EnvironmentRepository("", true, testEnv);
        Assert.assertEquals("/usr/share/java", repository.read("jAvA_homE"));
    }

    @Test
    public void testUpperCaseDisabledRead() throws RepositoryException {
        final TestSystem testEnv = new TestSystem();
        testEnv.putEnv("JAVA_HOME", "/usr/share/java");
        final Repository repository = new EnvironmentRepository("", false, testEnv);

        Assert.assertNull(repository.read("jAvA_homE"));
        Assert.assertEquals("/usr/share/java", repository.read("JAVA_HOME"));
    }

    @Test
    public void testPrefixedRead() throws RepositoryException {

        final TestSystem testEnv = new TestSystem();
        testEnv.putEnv("JAVA_HOME", "/usr/share/java");
        final Repository repository = new EnvironmentRepository("JAVA_", true, testEnv);

        Assert.assertEquals("/usr/share/java", repository.read("HOME"));
    }

    @Test
    public void testReadNames() {
        final TestSystem env = new TestSystem();
        env.putEnv("GRAYLOG_DATANODE_OPENSEARCH_NODE_NAME", "my-node");
        env.putEnv("GRAYLOG_DATANODE_OPENSEARCH_NODE_ROLE", "search");
        final Repository testRepository = new EnvironmentRepository("GRAYLOG_DATANODE_", true, env);
        final Collection<String> names = testRepository.readNames("OPENSEARCH_");
        Assert.assertEquals(2, names.size());
        Assert.assertTrue(names.contains("OPENSEARCH_NODE_NAME"));
        Assert.assertTrue(names.contains("OPENSEARCH_NODE_ROLE"));

        Assert.assertEquals("my-node", testRepository.read("OPENSEARCH_NODE_NAME"));
        Assert.assertEquals("search", testRepository.read("OPENSEARCH_NODE_ROLE"));
    }
}