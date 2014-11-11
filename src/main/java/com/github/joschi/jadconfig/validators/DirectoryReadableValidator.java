package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

import java.io.File;

public class DirectoryReadableValidator implements Validator<File> {

    @Override
    public void validate(String name, File value) throws ValidationException {
        if (value != null && value.isDirectory() && value.canRead()) {
            return;
        }
        throw new ValidationException("Cannot read from directory " + name + " at path " + value + ". Please specify the correct path or change the permissions");
    }
}