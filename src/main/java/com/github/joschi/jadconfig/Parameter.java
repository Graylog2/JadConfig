package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.NoConverter;
import com.github.joschi.jadconfig.validators.NoValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * Whether this parameter value should be trimmed before processing.
     *
     * @see Strings#trim(String)
     */
    boolean trim() default true;

    /**
     * Specific {@link Converter} to use for this parameter. This will override any configured {@link Converter} in
     * any {@link ConverterFactory} used by {@link JadConfig}.
     */
    Class<? extends Converter<?>> converter() default NoConverter.class;

    /**
     * Specific {@link Validator} to use for this parameter.
     *
     * @deprecated Use {@link #validators()} instead.
     */
    @Deprecated
    Class<? extends Validator<?>> validator() default NoValidator.class;

    /**
     * Specific {@link Validator}s to use for this parameter.
     */
    Class<? extends Validator<?>>[] validators() default {NoValidator.class};
}
