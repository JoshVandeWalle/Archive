package com.ogbc.archive.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookValidator implements ConstraintValidator<Book, String>
{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return s.matches("^[a-zA-Z0-9 ]+$") && s.length() <= 32;
    }
}
