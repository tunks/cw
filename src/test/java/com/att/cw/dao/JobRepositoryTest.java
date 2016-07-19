/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import javax.annotation.Resource;
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
/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test","dev"})
@ContextConfiguration(locations = "classpath:spring-jpa-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JobRepositoryTest {
    @Resource
    private JobRepository jobRepository;
    
    public JobRepositoryTest() {
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
     * Test of findByOwnerId method, of class JobRepository.
     */
    @Test
    public void testFindByOwnerId() {
        Job job = new Job("Job1", "Job1 description");
        Job result = jobRepository.save(job);
        assertEquals(job.getTitle(), result.getTitle());
    }   
    
}
