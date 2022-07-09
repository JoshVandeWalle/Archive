package com.ogbc.archive.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LettersAndSpacesValidator implements ConstraintValidator<LettersAndSpaces, String>
{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return s.matches("[a-zA-Z ]+$");
    }
}
