package com.ogbc.archive.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerseValidator implements ConstraintValidator<Verse, Integer>
{
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext)
    {
        return integer == null || (integer > 0 && integer <= 176);
    }
}
