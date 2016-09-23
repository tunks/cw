/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.Category;
import com.att.cw.model.QuestionCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test","dev"})
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
public class QuestionCategoryServiceTest {
    @Autowired
    private QuestionCategoryService questionCategoryService;
    private List<QuestionCategory> categories;
    public QuestionCategoryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
          categories = new ArrayList();
          for(Category c :Category.values()){
            categories.add( new QuestionCategory(c.name()));
          }
    }
    
    @After
    public void tearDown() {
       // questionCategoryService.deleteAll();
    }

    /**
     * Test of save method, of class QuestionCategoryService.
     */
    @Test
    public void testSave() {
      List<QuestionCategory> results  = categories.stream()
             .map(c -> {
                   return questionCategoryService.save(c);
             })
              .filter(c-> {return (c != null);})
              .collect(Collectors.toList());
       assertNotNull(results);
       assertTrue(results.size() > 0);
    }

    /**
     * Test of find method, of class QuestionCategoryService.
     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        Long id = null;
//        QuestionCategoryService instance = new QuestionCategoryService();
//        QuestionCategory expResult = null;
//        QuestionCategory result = instance.find(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class QuestionCategoryService.
//     */
//    @Test
//    public void testFindAll_0args() {
//        System.out.println("findAll");
//        QuestionCategoryService instance = new QuestionCategoryService();
//        List<QuestionCategory> expResult = null;
//        List<QuestionCategory> result = instance.findAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class QuestionCategoryService.
//     */
//    @Test
//    public void testFindAll_Pageable() {
//        System.out.println("findAll");
//        Pageable page = null;
//        QuestionCategoryService instance = new QuestionCategoryService();
//        Page<QuestionCategory> expResult = null;
//        Page<QuestionCategory> result = instance.findAll(page);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class QuestionCategoryService.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Long id = null;
//        QuestionCategoryService instance = new QuestionCategoryService();
//        instance.delete(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
