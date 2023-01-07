package com.evry.analytics.restController;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRestController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        BindingResult bindingResult =
                methodArgumentNotValidException.getBindingResult();

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        Map<String, String> errors = new HashMap<>();

        allErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
