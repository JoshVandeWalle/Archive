package com.ogbc.archive.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChapterValidator implements ConstraintValidator<Chapter, Integer>
{
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext)
    {
        return integer > 0 && integer <= 150;
    }
}
