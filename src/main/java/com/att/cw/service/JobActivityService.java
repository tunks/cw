/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobActivityRepository;
import com.att.cw.model.JobActivity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobActivityService
 *
 * @author ebrimatunkara
 */
@Service("jobActivityService")
public class JobActivityService implements CrudService<JobActivity, Long> {

    @Resource
    private JobActivityRepository jobActivityRepository;

    @Override
    public JobActivity save(JobActivity object) {
        return jobActivityRepository.save(object);
    }

    @Override
    public JobActivity find(Long id) {
        return jobActivityRepository.findOne(id);
    }

    @Override
    public List<JobActivity> findAll() {
        return (List<JobActivity>) jobActivityRepository.findAll();
    }

    @Override
    public Page<JobActivity> findAll(Pageable page) {
        return jobActivityRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobActivityRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
