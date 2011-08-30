package de.schalanda.jadconfig;

import java.lang.annotation.*;

/**
 * Annotation marking the validator methods in a configuration bean to facilitate more complex checks
 *
 * @author jschalanda
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidatorMethod {
}
