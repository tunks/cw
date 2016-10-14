/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.JobType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * JobTypeRepository
 * @author ebrimatunkara
 */
public interface JobTypeRepository extends PagingAndSortingRepository<JobType, Long>{
     public JobType findByName(String name);
}
