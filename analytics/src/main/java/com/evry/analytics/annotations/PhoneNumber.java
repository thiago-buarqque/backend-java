package com.evry.analytics.annotations;

import com.evry.analytics.annotations.constraintValidators.PhoneNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneNumberConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneNumber {
    String country() default "none";
    Class<?>[] groups() default {}; // What is it for?
    String message() default "Phone number is invalid";  // What is it for?
    Class<? extends Payload>[] payload() default {};
}
