package com.ogbc.archive.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChapterValidator.class)
public @interface Chapter
{
    String message() default "Invalid chapter (must be between 1-150 inclusive)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
