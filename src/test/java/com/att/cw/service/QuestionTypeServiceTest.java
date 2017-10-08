///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.att.cw.service;
//
//import com.att.cw.model.QuestionType;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// * QuestionType test class
// *
// * @author ebrimatunkara
// */
//@ActiveProfiles({"test", "dev"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
//@WebAppConfiguration
//public class QuestionTypeServiceTest {
//
//    @Autowired
//    private QuestionTypeService questionTypeService;
//
//    public QuestionTypeServiceTest() {
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
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of save method, of class QuestionTypeService.
//     */
////    @Test
////    public void testSave() {
////        System.out.println("save");
////        QuestionType object = null;
////        QuestionTypeService instance = new QuestionTypeService();
////        QuestionType expResult = null;
////        QuestionType result = instance.save(object);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    /**
//     * Test of find method, of class QuestionTypeService.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        QuestionType result = questionTypeService.findByName("TEXT");
//        assertNotNull(result);
//
//    }
//
//    /**
//     * Test of findAll method, of class QuestionTypeService.
//     */
//    @Test
//    public void testFindAll_0args() {
//        System.out.println("findAll");
//        List<QuestionType> result = questionTypeService.findAll();
//        assertTrue(result.size() > 1);
//    }
////
////    /**
////     * Test of findAll method, of class QuestionTypeService.
////     */
////    @Test
////    public void testFindAll_Pageable() {
////        System.out.println("findAll");
////        Pageable page = null;
////        QuestionTypeService instance = new QuestionTypeService();
////        Page<QuestionType> expResult = null;
////        Page<QuestionType> result = instance.findAll(page);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of delete method, of class QuestionTypeService.
////     */
////    @Test
////    public void testDelete() {
////        System.out.println("delete");
////        Long id = null;
////        QuestionTypeService instance = new QuestionTypeService();
////        instance.delete(id);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of deleteAll method, of class QuestionTypeService.
////     */
////    @Test
////    public void testDeleteAll() {
////        System.out.println("deleteAll");
////        QuestionTypeService instance = new QuestionTypeService();
////        instance.deleteAll();
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//
//}
