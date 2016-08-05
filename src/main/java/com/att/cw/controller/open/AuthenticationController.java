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
     * TODO -- controller exception https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
     * @param login, user credentials
     * @return response
     * @throws javax.servlet.ServletException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody final UserLogin login) throws ServletException {
        User user = userService.findByEmailAndPassword(login.getEmail(),login.getPassword());
        if (user != null) {
            //TODO --refactor
           // logger.info("User logged in successfully : " + user.getName() + ":" + user.getEnabled());
           LoginResponse response = new LoginResponse(Jwts.builder()
                                                          .setSubject(login.getEmail())
                                                          .claim("roles", user.getEmail())
                                                          .setIssuedAt(new Date())
                                                          .signWith(SignatureAlgorithm.HS256, "secretkey")
                                                          .compact());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        //logger.info("login failed....");
        return new ResponseEntity("Invalid crendentials!!", HttpStatus.FORBIDDEN);
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
        public String email;
        public String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
