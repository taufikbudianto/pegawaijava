package com.test.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author taufik.budiyanto
 * @date 08/03/2021
 * com.test.controller
 */
@Component
public abstract class ExceptionController{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            data.put(fieldName, errorMessage);
        });
        errors.put("rc","400");
        errors.put("errors",data);
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    protected Map<String, Object> handleValidationExceptionsParam(
            MissingServletRequestParameterException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put(ex.getParameterName(),ex.getMessage());
        errors.put("rc","400");
        errors.put("errors",data);
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected Map<String, Object> handleValidationExceptionsParamNotMatch(
            MethodArgumentTypeMismatchException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put(ex.getName(),ex.getMessage());
        errors.put("rc","400");
        errors.put("errors",data);
        return errors;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NoSuchElementException.class})
    protected Map<String, Object> handleValidationExceptionsDataNotfound(
            NoSuchElementException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put(ex.getLocalizedMessage(),ex.getMessage());
        errors.put("rc","500");
        errors.put("errors",data);
        return errors;
    }


}
