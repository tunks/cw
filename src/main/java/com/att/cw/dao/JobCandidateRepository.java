/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Job Candidate repository
 * @author ebrimatunkara
 */
public interface JobCandidateRepository extends PagingAndSortingRepository<JobCandidate, Long>{
//    /**
//     * Find job candidates by job in pages
//     * @param job
//     * @param page
//     * @return 
//     */
//    public Page<JobCandidate> findByJob(Job job, Pageable page);
//    /**
//     * Find all candidacies of a user
//     * @param user
//     * @return 
//     */
//    public Page<JobCandidate> findByUser(User user);
//    /**
//     * Find job candidacy of a user
//     * @param job
//     * @param user
//     * @return 
//     */
//    public JobCandidate findByJobAndUser(Job job, User user);
}
