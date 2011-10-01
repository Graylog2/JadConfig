package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.NoConverter;
import com.github.joschi.jadconfig.validators.NoValidator;

import java.lang.annotation.*;

/**
 * Annotation to mark a configuration parameter in a configuration bean
 *
 * @author jschalanda
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Parameter {

    /**
     * Name of the configuration option in the {@link Repository}
     */
    String value();

    /**
     * Whether this is a required parameter. If the parameter is missing but required {@link JadConfig} will
     * throw a {@link ParameterException}
     */
    boolean required() default false;

    /**
     * Specific {@link Converter} to use for this parameter. This will override any configured {@link Converter} in
     * any {@link ConverterFactory} used by {@link JadConfig}.
     */
    Class<? extends Converter<?>> converter() default NoConverter.class;

    /**
     * Specific {@link Validator} to use for this parameter.
     */
    Class<? extends Validator> validator() default NoValidator.class;
}
