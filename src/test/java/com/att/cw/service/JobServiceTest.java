/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.Job;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Job service class test unit
 * @author ebrimatunkara
 */
@ActiveProfiles({"test","dev"})
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
public class JobServiceTest {
    @Autowired
    private JobService jobService;

    private Job job;
    
    public JobServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
     job = new Job("Software engineer","This is the software engineer job description!");        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class JobService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Job result = jobService.save(job);
        assertNotNull(result);
    }

    /**
     * Test of find method, of class JobService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Long id = null;
        JobService instance = new JobService();
        Job expResult = null;
        Job result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
         fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class JobService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        JobService instance = new JobService();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class JobService.
     */
    @Test
    public void testFindAll_Pageable() {
        System.out.println("findAll");
        Pageable page = null;
        JobService instance = new JobService();
        Page<Job> expResult = null;
        Page<Job> result = instance.findAll(page);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    /**
     * Test of findAll method, of class JobService.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
        JobService instance = new JobService();
        List<Job> expResult = null;
        List<Job> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
