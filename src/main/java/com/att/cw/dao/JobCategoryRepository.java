/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.JobCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * JobCategoryRepository
 * @author ebrimatunkara
 */
public interface JobCategoryRepository extends PagingAndSortingRepository<JobCategory, Long>{
    public JobCategory findByName(String name);
}
