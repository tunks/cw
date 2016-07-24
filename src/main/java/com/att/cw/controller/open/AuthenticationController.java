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
 * Authentication controller -- Responsible for authentication mechanism via the
 * web
 *
 * @author ebrimatunkara
 */
@Controller("authenticate")
public class AuthenticationController {
    /**
     * TODO logger using AOP
     *
     */
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody final UserLogin login) throws ServletException {
        LoginResponse response = null;
        User user = userService.findByEmail(login.name);
        if (user != null) {
            //logger.info("User logged in successfully : " + user.getName() + ":" + user.getEnabled());
            response = new LoginResponse(Jwts.builder().setSubject(login.name)
                    .claim("roles", user.getEmailId()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        //logger.info("login failed....");
        return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);

    }

    @SuppressWarnings("unused")
    public static class LoginResponse {
        public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }
    }

    @SuppressWarnings("unused")
    public static class UserLogin {
        public String name;
        public String password;
    }
}
