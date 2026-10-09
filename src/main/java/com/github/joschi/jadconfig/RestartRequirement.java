package com.github.joschi.jadconfig;

/**
 * Describes whether changing a configuration parameter requires a restart of the application to take effect.
 *
 * @see Parameter#requiresRestart()
 */
public enum RestartRequirement {
    /**
     * It hasn't been determined whether changing the parameter requires a restart.
     */
    UNKNOWN,

    /**
     * Changing the parameter requires a restart.
     */
    REQUIRED,

    /**
     * Changing the parameter takes effect without a restart.
     */
    NOT_REQUIRED
}
