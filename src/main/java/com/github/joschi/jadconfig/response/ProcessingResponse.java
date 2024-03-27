package com.github.joschi.jadconfig.response;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProcessingResponse {

    private List<ProcessingOutcome> outcomes;

    public ProcessingResponse() {
        this.outcomes = new LinkedList<>();
    }

    public void addOutcome(final Object configurationBean,
                    final Map<String, Exception> fieldProcessingProblems,
                    final Map<String, Exception> validationMethodsProblems) {
        outcomes.add(new ProcessingOutcome(configurationBean, fieldProcessingProblems, validationMethodsProblems));
    }

    public List<ProcessingOutcome> getOutcomes() {
        return outcomes;
    }

    public boolean isSuccess() {
        return outcomes.stream().noneMatch(ProcessingOutcome::hasProblems);
    }
}

