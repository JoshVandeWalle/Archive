package com.ogbc.archive.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaNumAndSpacesValidator implements ConstraintValidator<AlphaNumAndSpaces, String>
{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return s.matches("^[a-zA-Z0-9 ]+$");
    }
}
