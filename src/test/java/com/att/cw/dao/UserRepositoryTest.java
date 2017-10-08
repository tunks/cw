/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.User;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;
    private String email;
    private String password;
    private Calendar calendar;

    public UserRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        email = RandomStringUtils.randomAlphanumeric(6).concat("@att.com");
        password = RandomStringUtils.randomAlphabetic(16);
        calendar = Calendar.getInstance();
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of Save method, of class UserRepository.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        User user = new User();
        
        user.setEmail(email);
        user.setPassword(password);
        //user.setDateOfBirth(new LocalDate());
        User result = userRepository.save(user);
        assertEquals(user, result);
    }

//    /**
//     * Test of findByEmailId method, of class UserRepository.
//     */
//    @Test
//    public void testFindByEmailId() {
//        System.out.println("findByEmailId");
//        String emailId = "";
//        User result = userRepository.findByEmailId(emailId);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findByEmailIdAndPassword method, of class UserRepository.
//     */
//    @Test
//    public void testFindByEmailIdAndPassword() {
//        System.out.println("findByEmailIdAndPassword");
//        String emailId = "";
//        String password = "";
//        UserRepository instance = new UserRepositoryImpl();
//        User expResult = null;
//        User result = instance.findByEmailIdAndPassword(emailId, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
