/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.model.User;
import com.att.cw.security.UserLogin;
import com.att.cw.service.SessionService;
import com.att.cw.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Authentication controller -- Responsible for user login authentication 
 * @author Dileep K Mundakkapatta
 */
@Controller
@RequestMapping("/open/authenticate")
public class AuthenticationController {
    
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private SessionService sessionService;
   // private UserService userService;
    /**
     * Authenticated user login
     * TODO -- controller exception https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
     * @param login, user credentials
     * @return response
     * @throws javax.servlet.ServletException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody final UserLogin login) throws ServletException
    {
    	
    	String token =  sessionService.login(login);
    	if(token == null)
    		return new ResponseEntity<String>("Authentication Failed",HttpStatus.UNAUTHORIZED );
    	else
    		return new ResponseEntity<String>(token,HttpStatus.OK);
    	
    }

  
}
