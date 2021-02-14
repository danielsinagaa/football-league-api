package com.enigma.Football.models.validations;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphabeticValidator implements ConstraintValidator<Alphabetic, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null ? s.matches("[A-Za-z]+") : true;
    }
}
