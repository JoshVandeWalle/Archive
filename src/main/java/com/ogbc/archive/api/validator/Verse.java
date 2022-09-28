package com.ogbc.archive.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerseValidator.class)
public @interface Verse
{
    String message() default "Invalid verse (must be between 1-176 inclusive)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}