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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Registration controller 
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("register")
public class RegistrationController {
   @Autowired
   private UserService userService;
    
   @RequestMapping(value="/new", method = RequestMethod.POST) 
   public ResponseEntity registerUser(final User user){
        User registeredUser = userService.save(user);
        if(registeredUser != null){
          /**
           * TODO send email asynchronously for confirmation using AOP
           **/
          return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
   }
}
