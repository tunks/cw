/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobApplicationRepository;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobApplication service
 * @author ebrimatunkara
 */
@Service("jobApplicationService")
public class JobApplicationService implements CrudService<JobApplication,Long>{
    @Resource
    private JobApplicationRepository jobApplicationRepository;
    /**
     * Save job object
     * @param object
     * @return 
     */
    @Override
    public JobApplication save(JobApplication object) {
       return jobApplicationRepository.save(object);
    }
    /**
     * Find job object by id
     * @param id
     * @return 
     */
    @Override
    public JobApplication find(Long id) {
       return jobApplicationRepository.findOne(id);
    }
    /**
     * Find all jobs
     * @return 
     */
    @Override
    public List<JobApplication> findAll() {
      return  (List<JobApplication>) jobApplicationRepository.findAll();
    }
    /**
     * Find all job applications by page
     * @param page
     * @return 
     */
    @Override
    public Page<JobApplication> findAll(Pageable page) {
        return  jobApplicationRepository.findAll(page);
    }
   
    /**
     * Delete job application 
     * @param id
     */
    @Override
    public void delete(Long id) {
         jobApplicationRepository.delete(id);
    }

    /**
     * Find list of job applications by page
     * @param job
     * @param page
     * @return 
     */    
    public Page<JobApplication> findByJob(Job job, Pageable page){
        return jobApplicationRepository.findByJob(job, page);
    }
    
    /**
     * Find job application by id and job
     * @param id
     * @param job
     * @return 
     */
    public JobApplication findByIdAndJob(Long id, Job job){
       return jobApplicationRepository.findByIdAndJob(id,job);
    }

    List<JobApplication> findByJob(Job job) {
      return jobApplicationRepository.findByJob(job);
    }
}
