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
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("/authenticate")
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
    public ResponseEntity login(@RequestBody final UserLogin login) throws ServletException
    {
    	
    	String token =  sessionService.login(login);
    	if(token == null)
    		return new ResponseEntity("Authentication Failed",HttpStatus.UNAUTHORIZED );
    	else
    		return new ResponseEntity(token,HttpStatus.OK);
    	
      //  User user = userService.findByEmailAndPassword(login.getEmail(),login.getPassword());
    /*    if (user != null) {
           
        	
        	
        	//TODO --refactor
           // logger.info("User logged in successfully : " + user.getName() + ":" + user.getEnabled());
         /  LoginResponse response = new LoginResponse(Jwts.builder()
                                                          .setSubject(login.getEmail())
                                                          .claim("roles", user.getEmail())
                                                          .setIssuedAt(new Date())
                                                          .signWith(SignatureAlgorithm.HS256, "secretkey")
                                                          .compact());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        //logger.info("login failed....");
        return new ResponseEntity("Invalid crendentials!!", HttpStatus.FORBIDDEN);*/
    }

  
}
