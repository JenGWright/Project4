package org.example.Controller.Validator;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = {NotIntigerValidator.class})
@Target({METHOD, FIELD,PARAMETER,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInteger {
    String message() default "Invalid input. Expected a string, found Integer. ";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
