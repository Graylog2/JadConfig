package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.repositories.InMemoryRepository;
import com.github.joschi.jadconfig.response.ProcessingOutcome;
import com.github.joschi.jadconfig.response.ProcessingResponse;
import com.github.joschi.jadconfig.testbeans.ValidatedConfigurationBean;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JadConfigLazyProcessingTest {

    private JadConfig jadConfig;
    private Repository repository;


    @Test
    public void testProcess() throws RepositoryException {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("test.byte","1");
        properties.put("test.short","2");
        properties.put("test.integer","-3");//negative, smaller than test.short
        properties.put("test.integer.port","70000"); //bigger than allowed port
        properties.put("test.long","4");
        properties.put("test.string","Test");
        repository = new InMemoryRepository(properties);
        ValidatedConfigurationBean configurationBean = new ValidatedConfigurationBean();
        jadConfig = new JadConfig(repository, configurationBean);

        ProcessingResponse response = jadConfig.processFailingLazily();
        assertFalse(response.isSuccess());
        assertEquals(1, response.getOutcomes().size());
        ProcessingOutcome processingOutcome = response.getOutcomes().get(0);
        assertEquals(configurationBean, processingOutcome.getConfigurationBean());
        Map<String, Exception> fieldProcessingProblems = processingOutcome.getFieldProcessingProblems();
        assertEquals(2, fieldProcessingProblems.size());
        assertTrue(fieldProcessingProblems.containsKey("test.integer"));
        assertTrue(fieldProcessingProblems.containsKey("test.integer.port"));

        Map<String, Exception> validationMethodsProblems = processingOutcome.getValidationMethodsProblems();
        assertEquals(1, validationMethodsProblems.size());
        assertTrue(validationMethodsProblems.containsKey("validate"));
    }
}
