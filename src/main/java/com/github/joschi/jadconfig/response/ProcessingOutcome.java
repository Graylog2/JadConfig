package com.github.joschi.jadconfig.response;

import java.util.Map;

public class ProcessingOutcome {

    private final Object configurationBean;
    private final Map<String, Exception> fieldProcessingProblems;
    private final Map<String, Exception> validationMethodsProblems;

    public ProcessingOutcome(final Object configurationBean,
                             final Map<String, Exception> fieldProcessingProblems,
                             final Map<String, Exception> validationMethodsProblems) {
        this.configurationBean = configurationBean;
        this.fieldProcessingProblems = fieldProcessingProblems;
        this.validationMethodsProblems = validationMethodsProblems;
    }

    public boolean hasProblems() {
        return (fieldProcessingProblems != null && !fieldProcessingProblems.isEmpty()) ||
                (validationMethodsProblems != null && !validationMethodsProblems.isEmpty());
    }

    public Object getConfigurationBean() {
        return configurationBean;
    }

    public Map<String, Exception> getFieldProcessingProblems() {
        return fieldProcessingProblems;
    }

    public Map<String, Exception> getValidationMethodsProblems() {
        return validationMethodsProblems;
    }
}
