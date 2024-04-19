package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.response.ProcessingOutcome;
import com.github.joschi.jadconfig.response.ProcessingResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class LazyValidationException extends ValidationException {
    private final ProcessingResponse processingResponse;

    public LazyValidationException(ProcessingResponse result) {
        super(toMessage(result));
        this.processingResponse = result;
    }

    private static String toMessage(ProcessingResponse result) {
        final List<String> stringBuilder = new LinkedList<>();
        stringBuilder.add("Following errors ocurred during configuration processing:");
        result.getOutcomes().stream()
                .filter(ProcessingOutcome::hasProblems)
                .flatMap(processingOutcome -> Stream.concat(
                        processingOutcome.getFieldProcessingProblems().values().stream().map(e -> toMessage(processingOutcome, e)),
                        processingOutcome.getValidationMethodsProblems().values().stream().map(e -> toMessage(processingOutcome, e))
                )).forEach(stringBuilder::add);
        return String.join("\n", stringBuilder);
    }

    private static String toMessage(ProcessingOutcome processingOutcome, Exception exception) {
        // TODO: should we distinct between field processing problem and validation method?
        // TODO: should we include class name of the bean or not?
        return exception.getMessage();
    }

    public ProcessingResponse getProcessingResponse() {
        return processingResponse;
    }
}
