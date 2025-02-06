package com.github.joschi.jadconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is intended to collect all configuration properties starting with the same prefix.
 * Think opensearch.path.repo and opensearch.node.roles values aggregated as a {@code Map<String,String>}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AggregatedParameter {
    /**
     * The prefix that will be used to match configuration properties. There is no preprocessing and it will be used
     * as defined. So if you want to match opensearch.path.repo and opensearch.node.roles, your prefix should be
     * {@code opensearch.}
     */
    String[] prefix();

    /**
     * Should the prefix be removed from collected property names?
     */
    boolean stripPrefix() default false;
}
