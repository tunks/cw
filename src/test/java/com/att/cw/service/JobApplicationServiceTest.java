/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import java.util.List;
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
public class JobApplicationServiceTest {
     @Autowired
    private JobApplicationService jobApplicationService;
    public JobApplicationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class JobApplicationService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        JobApplication object = null;
        JobApplicationService instance = new JobApplicationService();
        JobApplication expResult = null;
        JobApplication result = instance.save(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class JobApplicationService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Long id = null;
        JobApplicationService instance = new JobApplicationService();
        JobApplication expResult = null;
        JobApplication result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class JobApplicationService.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
        JobApplicationService instance = new JobApplicationService();
        List<JobApplication> expResult = null;
        List<JobApplication> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class JobApplicationService.
     */
    @Test
    public void testFindAll_Pageable() {
        System.out.println("findAll");
        Pageable page = null;
        JobApplicationService instance = new JobApplicationService();
        Page<JobApplication> expResult = null;
        Page<JobApplication> result = instance.findAll(page);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class JobApplicationService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        JobApplicationService instance = new JobApplicationService();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByJob method, of class JobApplicationService.
     */
    @Test
    public void testFindByJob() {
        System.out.println("findByJob");
        Job job = null;
        Pageable page = null;
        JobApplicationService instance = new JobApplicationService();
        Page<JobApplication> expResult = null;
        Page<JobApplication> result = instance.findByJob(job, page);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdAndJob method, of class JobApplicationService.
     */
    @Test
    public void testFindByIdAndJob() {
        System.out.println("findByIdAndJob");
        Long id = null;
        Job job = null;
        JobApplicationService instance = new JobApplicationService();
        JobApplication expResult = null;
        JobApplication result = instance.findByIdAndJob(id, job);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
