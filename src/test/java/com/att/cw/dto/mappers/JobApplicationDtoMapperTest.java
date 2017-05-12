/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dao.MockJobApplicationDao;
import com.att.cw.dto.JobApplicationEntryDto;
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
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springmvc-servlet.xml",
    "file:src/main/webapp/WEB-INF/root-context.xml",
    "file:src/main/webapp/WEB-INF/applicationContext-security.xml"})
@WebAppConfiguration
public class JobApplicationDtoMapperTest extends MockJobApplicationDao{
    
    public JobApplicationDtoMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         this.setDummyJob();
         this.setupDummyJobApplication();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of mapEntityIntoDTO method, of class JobApplicationDtoMapper.
     */
    @Test
    public void testMapEntityIntoDTO_JobApplication() {
        System.out.println("mapEntityIntoDTO");    
        JobApplicationEntryDto result = JobApplicationDtoMapper.mapEntityIntoDTO(application);
        assertNotNull(result);
    }

    /**
     * Test of mapEntityIntoDTO method, of class JobApplicationDtoMapper.
     */
    @Test
    public void testMapEntityIntoDTO_JobApplication_Job() {
        System.out.println("mapEntityIntoDTO");
        JobApplicationEntryDto result = JobApplicationDtoMapper.mapEntityIntoDTO(application, job);
        assertNotNull(result);
    }
    
     /**
     * Test of mapEntityIntoDTO method, of class JobApplicationDtoMapper.
     */
    @Test
    public void testMapEntityIntoDTO_JobApplication_Null() {
        System.out.println("mapEntityIntoDTO");
        JobApplicationEntryDto result = JobApplicationDtoMapper.mapEntityIntoDTO(null, job);
        assertNotNull(result);
    }
    
}
