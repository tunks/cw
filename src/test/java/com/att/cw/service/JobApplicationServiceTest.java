/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.FileDocumentRepository;
import com.att.cw.dao.FileDocumentRepositoryTest;
import com.att.cw.model.FileDocument;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.Resume;
import com.att.cw.support.ResourceType;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
 * Job application service test class
 * @author ebrimatunkara
 */
@ActiveProfiles({"test","dev"})
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
public class JobApplicationServiceTest {
    @Autowired
    private JobService  jobService;
    
    @Autowired
    private JobApplicationService jobApplicationService;
    
    @Autowired
    private ResumeService resumeService;
    
    @Autowired
    private FileDocumentService documentService;
    
    private JobApplication application;
    
    Job jobResult;
     
    public JobApplicationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.set(Calendar.MONTH, 2);
        Date closeDate = cal.getTime();
        JobVacancy vacancy = new JobVacancy();
        vacancy.setOpenDate(startDate);
        vacancy.setCloseDate(closeDate);
        Job job = new Job("Java developer II","Experience in java technologies");
        job.setVacancy(vacancy);
        //save job
        jobResult = jobService.save(job);
        FileDocument document = FileDocumentRepositoryTest.createMockDocument();
        document.setResourceType(ResourceType.JOB_APPLICATION);
        //save document
        documentService.save(document);
        //job resume
        Resume resume = new Resume();
        resume.setDocument(document);
        resumeService.save(resume);
//        //job application
//        application = new JobApplication();
//        application.setResume(resume);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class JobApplicationService.
     */
    @Test
    public void testSave() {
        //JobApplication result = jobApplicationService.save(application);
        //assertNotNull(result);
    }

    /**
     * Test of find method, of class JobApplicationService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
    }

    /**
     * Test of findAll method, of class JobApplicationService.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
    }

    /**
     * Test of findAll method, of class JobApplicationService.
     */
    @Test
    public void testFindAll_Pageable() {
        System.out.println("findAll");
    }

    /**
     * Test of delete method, of class JobApplicationService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
    }

    /**
     * Test of findByJob method, of class JobApplicationService.
     */
    @Test
    public void testFindByJob() {
        System.out.println("findByJob");
    }

    /**
     * Test of findByIdAndJob method, of class JobApplicationService.
     */
    @Test
    public void testFindByIdAndJob() {
        System.out.println("findByIdAndJob");

    }
    
}
