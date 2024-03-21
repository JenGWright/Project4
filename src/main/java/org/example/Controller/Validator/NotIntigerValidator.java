package org.example.Controller.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotIntigerValidator implements ConstraintValidator<NotInteger, String> {
    @Override
    public void initialize (NotInteger constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    
}
