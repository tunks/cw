///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.att.cw.service;
//
//import com.att.cw.model.Job;
//import com.att.cw.model.JobVacancy;
//import com.att.cw.support.DataTypeHelper;
//import java.nio.charset.StandardCharsets;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// * Job service class test unit
// *
// * @author ebrimatunkara
// */
//@ActiveProfiles({"test", "dev"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
//@WebAppConfiguration
//public class JobServiceTest {
//
//    @Autowired
//    private JobService jobService;
//
//    private Job job;
//
//    public JobServiceTest() {
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
//        Calendar cal = Calendar.getInstance();
//        Date openDate = cal.getTime();
//        //set close date
//        cal.add(Calendar.MONTH, 2);
//        Date closeDate = cal.getTime();
//        byte[] description = DataTypeHelper.stringToByte("This is the software engineer job description!");
//        JobVacancy vacancy = new JobVacancy(openDate, closeDate);
//        job = new Job("Software engineer", description);
//        job.setVacancy(vacancy);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of save method, of class JobService.
//     */
//    @Test
//    public void testSave() {
//        System.out.println("save");
//        Job result = jobService.save(job);
//        assertNotNull(result);
//    }
//
//    /**
//     * Test of find method, of class JobService.
//     */
////    @Test
////    public void testFind() {
////        System.out.println("find");
////        Long id = new Long(3);
////        Job result = jobService.find(id);
////        assertNotNull(result);
////    }
//    /**
//     * Test of delete method, of class JobService.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
////        Long id = null;
////        JobService instance = new JobService();
////        instance.delete(id);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class JobService.
//     */
//    @Test
//    public void testFindAll_Pageable() {
//        System.out.println("findAll");
//
//    }
//
//    /**
//     * Test of findAll method, of class JobService.
//     */
//    @Test
//    public void testFindAll_0args() {
//        System.out.println("findAll");
//        List<Job> result = jobService.findAll();
//        assertTrue(result.size() > 0);
//    }
//
//}
