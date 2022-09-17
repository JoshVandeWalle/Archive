package com.ogbc.archive.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TopicValidator implements ConstraintValidator<Topic, String>
{
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return s.matches("[a-zA-Z ]+$") && s.length() <= 128;
    }
}
