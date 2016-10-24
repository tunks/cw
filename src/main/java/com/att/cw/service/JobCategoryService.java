/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobCategoryRepository;
import com.att.cw.model.JobCategory;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobCategoryService
 * @author ebrimatunkara
 */
@Service("jobCategoryService")
public class JobCategoryService implements CrudService<JobCategory,Long>{
    @Resource
    private JobCategoryRepository categoryRepository;
    
    @Override
    public JobCategory save(JobCategory object) {
       return categoryRepository.save(object);
    }

    @Override
    public JobCategory find(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<JobCategory> findAll() {
        return (List<JobCategory>) categoryRepository.findAll();
    }

    @Override
    public Page<JobCategory> findAll(Pageable page) {
        return categoryRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void deleteAll() {
       categoryRepository.deleteAll();
    }    

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
