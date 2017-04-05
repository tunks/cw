/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobAnswerOptionRepository;
import com.att.cw.model.JobAnswerOption;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobAnswerOptionService
 *
 * @author ebrimatunkara
 */
@Service("jobAnswerOptionService")
public class JobAnswerOptionService implements CrudService<JobAnswerOption, Long> {

    @Resource
    private JobAnswerOptionRepository jobAnswerOptionRepository;

    @Override
    public JobAnswerOption save(JobAnswerOption object) {
        return jobAnswerOptionRepository.save(object);
    }

    @Override
    public JobAnswerOption find(Long id) {
        return jobAnswerOptionRepository.findOne(id);
    }

    @Override
    public List<JobAnswerOption> findAll() {
        return (List<JobAnswerOption>) jobAnswerOptionRepository.findAll();
    }

    @Override
    public Page<JobAnswerOption> findAll(Pageable page) {
        return jobAnswerOptionRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobAnswerOptionRepository.delete(id);
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
