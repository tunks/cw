/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.service.JobApplicationService;
import com.att.cw.service.JobService;
import com.att.cw.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ebrimatunkara
 */
@RestController
public class JobApplicationController implements  BaseController<JobApplication,Long>{
     /**
     * Job service instance
     */
    @Autowired
    private JobService jobService;
        /**
     * Job application service
     */
    @Autowired
    private JobApplicationService jobApplicationService;
    
        /**
     * Post job applications
     * @param id
     * @param application
     * @return
     */
    @RequestMapping(value = "/restricted/jobs/{id}/applications", method = RequestMethod.POST)
    public JobApplication saveApplication(@PathVariable Long id, @RequestBody JobApplication application) {
         Job job = jobService.find(id);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(id);
        }
        //map the job object
        application.setJob(job);
        return create(application);
    }
    
    /**
     * Find job applications by page
     * @param id
     * @param page
     * @return 
     */
     @RequestMapping(value = "/restricted/jobs/{id}/applications", method = RequestMethod.GET)
    public Page<JobApplication> findApplications(@PathVariable Long id, Pageable page) {
        Job job = jobService.find(id);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(id);
        }
        //find job applications
        return jobApplicationService.findByJob(job, page);
    }
    /**
     * Find job application
     * @param jobId
     * @param id
     * @return 
     */
     @RequestMapping(value = "/restricted/jobs/{jobId}/applications/{id}", method = RequestMethod.GET)
    public JobApplication findApplication(@PathVariable Long jobId, @PathVariable Long id) {
        Job job = jobService.find(jobId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
        }
        //find job application
        return jobApplicationService.findByIdAndJob(id, job);
    }
    
     /**
     * Find job application
     * @param jobId
     * @param id 
     */
     @RequestMapping(value = "/{jobId}/applications/{id}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable Long jobId, @PathVariable Long id) {
        Job job = jobService.find(jobId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
        }
       //delete job application
        delete(id);
    }

    @Override
    public JobApplication find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
           jobApplicationService.delete(id);
    }

    @Override
    public JobApplication create(JobApplication object) {
        //save the job application object
        return jobApplicationService.save(object);
    }

    @Override
    public JobApplication update(JobApplication object) {
         return jobApplicationService.save(object);
    }
}
