/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.JobCategoryDto;
import com.att.cw.model.JobCategory;
import com.att.cw.service.JobCategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobCategory controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/jobcategories")
public class JobCategoryController implements BaseController<JobCategory, Long> {

    /**
     * JobCategoryService
     */
    @Autowired
    private JobCategoryService jobCategoryService;

    /**
     * Find all job categories
     *
     * @return , list of all job categories
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<JobCategoryDto> findAll() {
        return jobCategoryService.findAll()
                .stream()
                .map(c -> {
                    return new JobCategoryDto(c.getId(), c.getName());
                }).collect(Collectors.toList());
    }

    /**
     * Find job category by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JobCategory find(@PathVariable Long id) {
        return jobCategoryService.find(id);
    }

    /**
     * Delete all job categories
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Override
    public void deleteAll() {
        jobCategoryService.deleteAll();
    }

    /**
     * Delete job category by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(Long id) {
        jobCategoryService.delete(id);
    }

    /**
     * Create job category
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public JobCategory create(JobCategory object) {
        return jobCategoryService.save(object);
    }

    /**
     * Update job category
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public JobCategory update(JobCategory object) {
        return jobCategoryService.save(object);
    }

}
