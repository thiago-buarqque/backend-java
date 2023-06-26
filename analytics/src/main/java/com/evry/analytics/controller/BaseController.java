package com.evry.analytics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        Map<String, String> errors = new HashMap<>();

        for (ObjectError objectError : allErrors) {
            String fieldName = ((FieldError) objectError).getField();

            String errorMessage = objectError.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        }

        return errors;
    }
}
