/**
 *
 */
package com.att.cw.controller;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.att.cw.controller.open.RegistrationController;
import com.att.cw.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Dileep K Mundakkapatta
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionControllerAdvice.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> ValidationExceptionHandler(ValidationException ex) {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("Validation Exception");
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationExceptionHandler(ConstraintViolationException ex) throws JsonProcessingException {
        //ex.printStackTrace();
         Map<String,String> errorMessages = Collections.synchronizedMap(new HashMap());
         for(final ConstraintViolation<?> v : ex.getConstraintViolations()){
             
             errorMessages.put(v.getPropertyPath().toString(),v.getMessage());
         }
        String errorJson = new ObjectMapper().writeValueAsString(errorMessages);
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("Constraint Violation");
        error.setErrorMessage(errorJson);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
        @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) throws JsonProcessingException {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("Constraint Violation");
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
//
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("MethodArgumentNotValid Exception");
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> InvalidFormatExceptionHandler(InvalidFormatException ex) {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("Invalid Format Exception");
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorTitle("Bad request Format");
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}
