package com.github.joschi.jadconfig;

import io.toolisticon.cute.Cute;
import io.toolisticon.cute.CuteApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParameterTypesValidatorTest {

    CuteApi.BlackBoxTestSourceFilesInterface compileTestBuilder;

    @BeforeEach
    public void init() {
        compileTestBuilder = Cute
                .blackBoxTest()
                .given()
                .processor(ParameterTypesValidator.class);
    }

    @Test
    public void testPropertyValidatorProcessor() {
        compileTestBuilder
                .andSourceFiles("/com/github/joschi/jadconfig/MyAnnotationValidatorTestClass.java")
                .whenCompiled().thenExpectThat()
                .compilationFails()
                .andThat()
                .compilerMessage()
                .ofKindError().equals("Property my_int assigned to field MyAnnotationValidatorTestClass#myIntField has type java.lang.Integer but converter expects java.lang.Long")
                .andThat()
                .compilerMessage()
                .ofKindError().equals("Property my_duration assigned to field MyAnnotationValidatorTestClass#myDurationField has type java.time.Duration but validator expects com.github.joschi.jadconfig.util.Duration")
                .executeTest();
    }
}