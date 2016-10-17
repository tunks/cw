/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobTypeRepository;
import com.att.cw.model.JobType;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobTypeService 
 * @author ebrimatunkara
 */
@Service("jobtypeService")
public class JobTypeService implements CrudService<JobType,Long>{
    @Resource
    private JobTypeRepository jobtypeRepository;

    @Override
    public JobType save(JobType object) {
       return jobtypeRepository.save(object);
    }

    @Override
    public JobType find(Long id) {
       return jobtypeRepository.findOne(id);
    }

    @Override
    public List<JobType> findAll() {
       return (List<JobType>) jobtypeRepository.findAll();
    }

    @Override
    public Page<JobType> findAll(Pageable page) {
       return jobtypeRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobtypeRepository.delete(id);
    }

    @Override
    public void deleteAll() {
       jobtypeRepository.deleteAll();
    }
}
