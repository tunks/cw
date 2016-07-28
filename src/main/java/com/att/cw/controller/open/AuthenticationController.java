/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.model.User;
import com.att.cw.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Authentication controller -- Responsible for user login authentication 
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("/authenticate")
public class AuthenticationController {
    /**
     * TODO logger using AOP
     */
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    /**
     * Authenticated user login
     * @param login, user credentials
     * @return response
     * @throws javax.servlet.ServletException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody final UserLogin login) throws ServletException {
        LoginResponse response = null;
        User user = userService.findByEmailOrName(login.name,login.name);
        if (user != null) {
           // logger.info("User logged in successfully : " + user.getName() + ":" + user.getEnabled());
            response = new LoginResponse(Jwts.builder().setSubject(login.name)
                    .claim("roles", user.getEmailId()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        //logger.info("login failed....");
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
    
    public static class LoginResponse implements Serializable{
        public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class UserLogin implements Serializable{
        public String name;
        public String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        
        
    }
}
