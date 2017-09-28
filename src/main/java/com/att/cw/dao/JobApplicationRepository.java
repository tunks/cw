/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * JobApplication Repository
 *
 * @author ebrimatunkara
 */
public interface JobApplicationRepository extends PagingAndSortingRepository<JobApplication, Long> {

    /**
     * Find job applications by application
     *
     * @param job
     * @param page
     * @return
     */
    public Page<JobApplication> findByJob(Job job, Pageable page);

    /**
     * Find job application by id and job
     *
     * @param id
     * @param job
     * @return
     */
    public JobApplication findByIdAndJob(Long id, Job job);

    /**
     * Find by job
     *
     * @param job
     * @return ,return list of jobs
     */
    public List<JobApplication> findByJob(Job job);
     /**
      * Find by job and candidate
     * @param job
     * @param candidate
     * @return 
      **/
    public JobApplication findByCandidateAndJob(JobCandidate candidate,Job job);
}
