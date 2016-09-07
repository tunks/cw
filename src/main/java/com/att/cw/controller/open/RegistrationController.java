/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.dao.AuthorityRepository;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.model.User;
import com.att.cw.service.RegistrationService;
import com.att.cw.service.UserService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Registration controller 
 * @author ebrimatunkara
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
   public ResponseEntity registerUser(@RequestBody @Valid final UserRegistrationDto user) throws Exception
   {
	   
        	regService.registerUser(user);
            /**
             * TODO send email asynchronously for confirmation using AOP
            **/
             return new ResponseEntity("User is successfully registered, check your email to activate your account!",HttpStatus.CREATED);
        
   }
   @RequestMapping(value="/confirm/{token:.+}", method = RequestMethod.GET) 
   public ResponseEntity confirmUser(@PathVariable final String token) throws Exception
   {
	   
        	logger.info("Toen is : "+token);
        	regService.activateUser(token);
            /**
             * TODO send email asynchronously for confirmation using AOP
            **/
             return new ResponseEntity("User successfully Activated",HttpStatus.OK);
        
   }
}
