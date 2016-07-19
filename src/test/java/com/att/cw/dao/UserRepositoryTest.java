/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.User;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@ContextConfiguration(locations = "classpath:spring-jpa-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    private String emailId;
    private String password;
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
        emailId = "user1@att.com";
        password = "fsd9232sd577s";  
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
        user.setEmailId(emailId);
        user.setPassword(password);
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
