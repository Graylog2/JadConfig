package com.github.joschi.jadconfig.response;

import java.util.List;

public class ProcessingResponse {

    private final List<ProcessingOutcome> outcomes;

    public ProcessingResponse(List<ProcessingOutcome> outcomes) {
        this.outcomes = outcomes;
    }

    public List<ProcessingOutcome> getOutcomes() {
        return outcomes;
    }

    public boolean isSuccess() {
        return outcomes.stream().noneMatch(ProcessingOutcome::hasProblems);
    }
}

