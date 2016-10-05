package com.att.cw.controller.restricted;

import com.att.cw.controller.BaseController;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.service.JobApplicationService;
import com.att.cw.service.JobService;
import com.att.cw.support.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController rest controller implementation of BaseController
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/jobs")
public class JobController implements BaseController<Job, Long> {
    /**
     * Job service instance
     *
     */
    @Autowired
    private JobService jobService;
    /**
     * Job application service
     */
    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * find all
     *
     * @param ownerId
     * @param page
     * @return 
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<Job> findAllBy(@RequestParam(value = "groupId", required = false) Long ownerId, Pageable page) {
        // if(ownerId != null)
        //   return jobService.findAllByOwner(ownerId, page);
        return jobService.findAll(page);
    }

    /**
     * find and return job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public Job find(@PathVariable Long id) {
        return jobService.find(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * delete job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    @ResponseBody
    public void delete(@PathVariable Long id) {
        jobService.delete(id);
    }

    /**
     * Create and return new job entity
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public Job create(@RequestBody Job object) {
        return jobService.save(object);
    }

    /**
     * Update and return existing job entity
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public Job update(@RequestBody Job object) {
        return jobService.save(object);
    }

    /**
     * Post job applications
     *
     * @param jobId
     * @param application
     * @return
     */
    @RequestMapping(value = "/{jobId}/applications", method = RequestMethod.POST)
    public JobApplication saveJobApplication(@PathVariable Long jobId, @RequestBody JobApplication application) {
        Job job = jobService.find(jobId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
        }
        //map the job object
        application.setJob(job);
        //save the job application object
        return jobApplicationService.save(application);
    }
    
    /**
     * Find job applications by page
     * @param jobId
     * @param page
     * @return 
     */
     @RequestMapping(value = "/{jobId}/applications", method = RequestMethod.GET)
    public Page<JobApplication> findJobApplications(@PathVariable Long jobId, Pageable page) {
        Job job = jobService.find(jobId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
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
     @RequestMapping(value = "/{jobId}/applications/{id}", method = RequestMethod.GET)
    public JobApplication findJobApplication(@PathVariable Long jobId, @PathVariable Long id) {
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
    public void deleteJobApplication(@PathVariable Long jobId, @PathVariable Long id) {
        Job job = jobService.find(jobId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
        }
       //delete job application
       jobApplicationService.delete(id);
    }
}
