/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.model.User;
import com.att.cw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Registration controller 
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
   @Autowired
   private UserService userService;
    
   /**
    * Register new user account
    * TODO - Controller exception handling https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
    * @param user
    * @return 
    */
   @RequestMapping(value="/new", method = RequestMethod.POST) 
   public ResponseEntity registerUser(@RequestBody final User user){
        if(!userService.existsByEmail(user.getEmail())){
            User registeredUser = userService.save(user);
            /**
             * TODO send email asynchronously for confirmation using AOP
            **/
            return new ResponseEntity("User is successfully registered, check your email to activate your account!",HttpStatus.CREATED);
        }
        else{
             return new ResponseEntity("Sorry, user account already registered!",HttpStatus.NOT_ACCEPTABLE);
        }
   }
}
