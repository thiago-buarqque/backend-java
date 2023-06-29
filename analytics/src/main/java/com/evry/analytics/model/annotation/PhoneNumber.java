package com.evry.analytics.model.annotation;

import com.evry.analytics.model.annotation.constraintValidator.PhoneNumberConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PhoneNumberConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneNumber {

    boolean required() default false;

    String country() default "none";

    Class<?>[] groups() default {};

    String message() default "Phone number is invalid";

    Class<? extends Payload>[] payload() default {};
}
