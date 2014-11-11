package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

import java.io.File;

public class FileReadableValidator implements Validator<File> {

    @Override
    public void validate(String name, File value) throws ValidationException {
        if (value != null && value.isFile() && value.canRead()) {
            return;
        }
        throw new ValidationException("Cannot read file " + name + " at path " + value + ". Please specify the correct path or change the permissions");
    }
}