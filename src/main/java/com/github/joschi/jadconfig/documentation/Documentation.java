package com.github.joschi.jadconfig.documentation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to document configuration files. It allows autogeneration of config documentation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Documentation {
    /**
     * We don't want to expose some configuration fields to users. They are internal, required for system packages functionality
     * or deprecated. Set to false if you want to hide this field from documentation.
     */
    boolean visible() default true;

    /**
     * Description of this configuration property.
     */
    String value() default "";
}
