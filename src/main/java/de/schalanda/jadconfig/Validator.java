package de.schalanda.jadconfig;

/**
 * @author jschalanda
 */
public interface Validator {

    void validate(String name, String value) throws ValidationException;
}
