package com.capstone.realmen.common.validate.constrains;

import com.capstone.realmen.common.validate.Phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("[0-9]+") && value.length() <= 15;
    }

}
