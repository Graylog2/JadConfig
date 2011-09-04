package com.github.joschi.jadconfig;

import com.github.joschi.jadconfig.converters.NoConverter;
import com.github.joschi.jadconfig.validators.NoValidator;

import java.lang.annotation.*;

/**
 * @author jschalanda
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Parameter {

    String value();
    boolean required() default false;
    Class<? extends Converter<?>> converter() default NoConverter.class;
    Class<? extends Validator> validator() default NoValidator.class;
}
