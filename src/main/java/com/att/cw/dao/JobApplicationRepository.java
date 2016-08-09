/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * JobApplication Repository
 * @author ebrimatunkara
 */
public interface JobApplicationRepository extends PagingAndSortingRepository<JobApplication, Long>{
      /**
       * Find job applications by application
       * @param job
       * @param page
       * @return 
       */
      public Page<JobApplication> findByJob(Job job, Pageable page);
}
