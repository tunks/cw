package com.att.cw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.att.cw.controller.open.RegistrationController;
import com.att.cw.dto.ErrorResponse;

@ControllerAdvice
public class ExceptionControllerAdvice 
{
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) 
	{
		logger.info("Exception is :"+ex.getMessage());
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setErrorTitle("Internal Server Error");
        error.setErrorMessage("Please contact your administrator");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
}
