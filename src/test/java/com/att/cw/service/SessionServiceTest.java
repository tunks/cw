///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.att.cw.service;
//
//import com.att.cw.security.UserLogin;
//import com.att.cw.security.UserSession;
//import java.util.Map;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// *
// * @author ebrimatunkara
// */
//@ActiveProfiles({"test", "dev"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
//@WebAppConfiguration
//public class SessionServiceTest {
//
//    @Autowired
//    private SessionService sessionService;
//    private UserLogin login;
//
//    public SessionServiceTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        login = new UserLogin.LoginBuild()
//                .setName("ebrima@localhost.local")
//                .setPassword("cw12345")
//                .create();
//    }
//
//    @After
//    public void tearDown() {
//    }
//
////    /**
////     * Test of save method, of class SessionService.
////     */
////    @Test
////    public void testSave() {
////        System.out.println("save");
////        UserSession session = null;
////        instance.save(session);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of find method, of class SessionService.
////     */
////    @Test
////    public void testFind() {
////        System.out.println("find");
////        String emailID = "";
////        SessionService instance = new SessionService();
////        UserSession expResult = null;
////        UserSession result = instance.find(emailID);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of delete method, of class SessionService.
////     */
////    @Test
////    public void testDelete() {
////        System.out.println("delete");
////        String emailID = "";
////        SessionService instance = new SessionService();
////        instance.delete(emailID);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    /**
//     * Test of login method, of class SessionService.
//     */
//    @Test
//    public void testLogin() {
//        System.out.println("login");
//        Map result = sessionService.login(login);
//        assertNotNull(result);
//    }
//
//}
