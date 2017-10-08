/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.controller.open.RegistrationController;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.model.Gender;
import com.att.cw.model.User;
import com.att.cw.security.UserLogin;
import com.att.cw.service.AuthorityService;
import com.att.cw.service.RegistrationService;
import com.att.cw.service.SessionService;
import com.att.cw.service.UserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ebrimatunkara
 */
public class MockUserDao {

    private static final Logger logger = LoggerFactory.getLogger(MockUserDao.class);

    @Autowired
    private RegistrationService regService;
    
    @Autowired
    private AuthorityService authorityService;

    @Resource
    protected UserService userService;

    @Autowired
    private SessionService sessionService;

    protected User createDummyData(UserRegistrationDto userDto) {
        try {
            User user = regService.registerUser(userDto);
            User tmp = userService.find(user.getId());
            tmp.setEnabled(Boolean.TRUE);
            return userService.save(tmp);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }
    }

    protected User createDummyData() {
        return createDummyData(registerUser());
    }

    protected boolean removeDummyData(User user) {
        try {
            userService.delete(user);
            return true;
        } catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
    }

    protected Map<String, Object> dummyUserLogin(UserLogin userLogin) {
        return sessionService.login(userLogin);
    }

    protected UserRegistrationDto registerUser() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = name + "@localhost.local";
        String password = RandomStringUtils.randomAlphanumeric(12);
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName(name);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setGender(Gender.MALE);
        userDto.setDateOfBirth(new LocalDate());
        return userDto;
    }
}
