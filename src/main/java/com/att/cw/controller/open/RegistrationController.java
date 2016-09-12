/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;


import com.att.cw.dto.ErrorResponse;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.exception.JwtTokenMalformedException;
import com.att.cw.exception.UserAlreadyExistingException;
import com.att.cw.service.RegistrationService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Registration controller 
 * @author Dileep K Mundakkapatta
 */
@Controller
@RequestMapping("/open/register")
public class RegistrationController {
	
   private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
   @Autowired
   private RegistrationService regService;
   
   
    
    
   /**
    * Register new user account
    * TODO - Controller exception handling https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
    * @param user
    * @return 
    */
   @RequestMapping(value="/new", method = RequestMethod.POST) 
   public ResponseEntity<String> registerUser(@RequestBody @Valid final UserRegistrationDto user)
   {
	   
        	regService.registerUser(user);
            return new ResponseEntity<String>("User successfully registered. Please check your email to activate your account!",HttpStatus.CREATED);
        
   }
   @RequestMapping(value="/confirm/{token:.+}", method = RequestMethod.GET) 
   public ResponseEntity<String> confirmUser(@PathVariable final String token)
   {
	   
        	logger.info("Toen is : "+token);
        	regService.activateUser(token);
            return new ResponseEntity<String>("User successfully Activated",HttpStatus.OK);
        
   }
   
   @ExceptionHandler({JwtTokenMalformedException.class})
   public ResponseEntity<ErrorResponse> exceptionHandler(JwtTokenMalformedException ex) 
   {
       ErrorResponse error = new ErrorResponse();
       error.setErrorCode(HttpStatus.BAD_REQUEST.value());
       error.setMessage(ex.getErrorMessage());
       return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler({UserAlreadyExistingException.class})
   public ResponseEntity<ErrorResponse> exceptionHandler(UserAlreadyExistingException ex) 
   {
       ErrorResponse error = new ErrorResponse();
       error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
       error.setMessage(ex.getErrorMessage());
       return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
   }

}
