package com.ogbc.archive.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookValidator.class)
public @interface Book
{
    String message() default "Invalid book (length must be less than or equal to 32 characters and contain only letters, numbers, and spaces)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
