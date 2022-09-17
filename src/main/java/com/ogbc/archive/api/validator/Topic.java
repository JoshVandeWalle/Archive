package com.ogbc.archive.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TopicValidator.class)
public @interface Topic
{
    String message() default "Invalid topic (length must be less than or equal to 128 characters and contain only letters and spaces)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
