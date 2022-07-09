package com.ogbc.archive.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LettersAndSpacesValidator.class)
public @interface LettersAndSpaces
{
    String message() default "A path variable or parameter included in the request can only contains letters and spaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
