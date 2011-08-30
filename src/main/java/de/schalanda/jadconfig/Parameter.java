package de.schalanda.jadconfig;

import de.schalanda.jadconfig.converters.NoConverter;
import de.schalanda.jadconfig.validators.NoValidator;

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
