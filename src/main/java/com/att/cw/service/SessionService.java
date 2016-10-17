package com.att.cw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.att.cw.security.JwtUtil;
import com.att.cw.security.UserLogin;
import com.att.cw.dao.SessionRepository;
import com.att.cw.model.User;
import com.att.cw.security.UserSession;

@Service
public class SessionService {
    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    @Qualifier("sessionRepository")
    private SessionRepository sessionRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public void save(UserSession session) {
        this.sessionRepo.save(session);

    }

    public UserSession find(String emailID) {
        return this.sessionRepo.find(emailID);
    }

    public void delete(String emailID) {
        this.sessionRepo.delete(emailID);

    }

    public String login(UserLogin userLogin) {
        logger.info("SessionService Called...");
        User user = userService.findByEmailAndPassword(userLogin.getName(), userLogin.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user);
            save(new UserSession(userLogin.getName(), token));
            return token;
        } else {
            logger.info("User is Null");
            return null;
        }
    }
}
