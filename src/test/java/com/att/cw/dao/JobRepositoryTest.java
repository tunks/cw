///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.att.cw.dao;
//
//import com.att.cw.model.Job;
//import com.att.cw.model.JobVacancy;
//import com.att.cw.support.DataTypeHelper;
//import java.nio.charset.StandardCharsets;
//import java.util.Calendar;
//import java.util.Date;
//import javax.annotation.Resource;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
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
//public class JobRepositoryTest {
//
//    @Resource
//    private JobRepository jobRepository;
//    private Job job;
//
//    public JobRepositoryTest() {
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
//        job = new Job("Project Manager", description);
//        job.setVacancy(vacancy);
//    }
//
//    @After
//    public void tearDown() {
//        jobRepository.delete(job);
//    }
//
//    /**
//     * Test of findByOwnerId method, of class JobRepository.
//     */
//    @Test
//    public void testFindByOwnerId() {
//        Job result = jobRepository.save(job);
//        assertEquals(job.getTitle(), result.getTitle());
//    }
//
//    /**
//     * Test of findByOwnerId method, of class JobRepository.
//     */
////    @Test
////    public void testFindByQuestion() {     
////        Job result = jobRepository.findByQuestionId(new Long(200));
////        assertNotNull(result);
////        System.out.println(result);
////    }
//}
