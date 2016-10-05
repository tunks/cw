/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobCandidateRepository;
import com.att.cw.model.Job;
import com.att.cw.model.JobCandidate;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Job Candidate service
 * @author ebrimatunkara
 */
@Service("jobCandidateService")
public class JobCandidateService implements CrudService<JobCandidate,Long>{
    @Resource
    private  JobCandidateRepository candidateRepository;
    /**
     * Save job candidate
     * @param object
     * @return 
     */
    @Override
    public JobCandidate save(JobCandidate object) {
       return candidateRepository.save(object);
    }

     /**
     * Find job candidate
     * @param id
     * @return 
     */
    @Override
    public JobCandidate find(Long id) {
      return candidateRepository.findOne(id);
    }

    /**
     * Find all job candidates
     * @return 
     */
    @Override
    public List<JobCandidate> findAll() {
      return (List<JobCandidate>) candidateRepository.findAll();
    }
    /**
     * Find all job candidates by paging
     * @param page
     * @return 
     */
    @Override
    public Page<JobCandidate> findAll(Pageable page) {
       return candidateRepository.findAll(page);
    }
    /**
     * Delete candidate by id
     * @param id 
     */
    @Override
    public void delete(Long id) {
        candidateRepository.delete(id);
    }
    /**
     * Find all job candidates by job and paging
     * @param job
     * @param page
     * @return 
     */
    public Page<JobCandidate> findAll(Job job, Pageable page) {
       return candidateRepository.findByJob(job, page);
    }   
}
