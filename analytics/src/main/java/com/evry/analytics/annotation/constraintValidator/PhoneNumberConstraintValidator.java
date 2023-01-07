package com.evry.analytics.annotation.constraintValidator;

import com.evry.analytics.annotation.PhoneNumber;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        _country = constraintAnnotation.country();
        _required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String mask = countriesMask.getOrDefault(_country, countriesMask.get("none"));

        if (value == null) {
            return true;
        }

        boolean empty = value.trim().equals("");

        boolean valid = value.matches(mask);

        if (_required) {
            return !empty && valid;
        }

        if (empty) {
            return true;
        }

        return valid;
    }

    private String _country;

    private final Map<String, String> countriesMask =
            new HashMap<String, String>() {
                {
                    put("Brazil", "^((\\+\\d{2}\\s)?\\(\\d{2}\\)\\s?\\d{4}\\d?\\-\\d{4})?$");
                    put("USA", "^((\\+1)?\\s?\\(\\d{3}\\)\\s?\\d{3}\\-\\d{4})?$");
                    put("none", ".");
                }
            };

    private boolean _required;
}
