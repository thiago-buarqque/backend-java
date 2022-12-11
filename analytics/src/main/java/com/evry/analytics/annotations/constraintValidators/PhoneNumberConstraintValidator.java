package com.evry.analytics.annotations.constraintValidators;

import com.evry.analytics.annotations.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberConstraintValidator implements
        ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        country = constraintAnnotation.country();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String mask =
                countriesMask.getOrDefault(country, countriesMask.get("none"));

        return value.matches(mask);
    }

    private String country;
    private final Map<String, String> countriesMask =
        new HashMap<String, String>() {
            {
                put("Brazil",
                    "^((\\+\\d{2}\\s)?\\(\\d{2}\\)\\s?\\d{4}\\d?\\-\\d{4})?$"
                );
                put("USA",
                    "^((\\+1)?\\s?\\(\\d{3}\\)\\s?\\d{3}\\-\\d{4})?$"
                );
                put("none",
                    "."
                );
            }
    };
}
