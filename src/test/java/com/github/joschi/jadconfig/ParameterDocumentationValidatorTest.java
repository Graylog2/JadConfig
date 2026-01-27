package com.github.joschi.jadconfig;

import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParameterDocumentationValidatorTest {

    CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder;

    @BeforeEach
    public void init() {
        compileTestBuilder = Cute
                .blackBoxTest()
                .given()
                .processor(ParameterDocumentationValidator.class);
    }

    @Test
    public void testDocumentationProcessor() {
        compileTestBuilder
                .andSourceFiles("/com/github/joschi/jadconfig/MyDocumentationValidatorTestClass.java")
                .whenCompiled().thenExpectThat()
                .compilationFails()
                .andThat()
                .compilerMessage()
                .ofKindError().atLine(18).atColumn(22).equals("Property my_duration assigned to field MyDocumentationValidatorTestClass#myDurationField has no documentation available. Please, add @Documentation annotation!")
                .executeTest();
    }
}