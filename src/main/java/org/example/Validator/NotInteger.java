package org.example.Validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {NotIntegerValidator.class})
@Target({METHOD, FIELD,PARAMETER,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInteger {
    String message() default "Invalid input. Expected a string, found Integer. ";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
