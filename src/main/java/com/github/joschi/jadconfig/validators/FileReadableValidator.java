package com.github.joschi.jadconfig.validators;

import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.Validator;

import java.io.File;

public class FileReadableValidator implements Validator {

    @Override
    public void validate(String name, String value) throws ValidationException {

        if (null == value) {
            throw new ValidationException("Cannot read null file. Please specify the correct path.");
        }

        File file = new File(value);

        if (file.isFile() && file.canRead()) {
            return;
        }
        throw new ValidationException("Cannot read file " + name + " at path " + value + ". Please specify the correct path or change the permissions");
    }
}