/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobQuestionAnswerRepository;
import com.att.cw.model.JobQuestionAnswer;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobQuestionAnswerService class
 *
 * @author ebrimatunkara
 */
@Service("jobQuestionAnswerService")
public class JobQuestionAnswerService implements CrudService<JobQuestionAnswer, Long> {

    @Resource
    private JobQuestionAnswerRepository jobQuestionAnswerRepository;

    @Override
    public JobQuestionAnswer save(JobQuestionAnswer object) {
        return jobQuestionAnswerRepository.save(object);
    }

    @Override
    public JobQuestionAnswer find(Long id) {
        return jobQuestionAnswerRepository.findOne(id);
    }

    @Override
    public List<JobQuestionAnswer> findAll() {
        return (List<JobQuestionAnswer>) jobQuestionAnswerRepository.findAll();
    }

    @Override
    public Page<JobQuestionAnswer> findAll(Pageable page) {
        return jobQuestionAnswerRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobQuestionAnswerRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        jobQuestionAnswerRepository.deleteAll();
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(JobQuestionAnswer object) {
        jobQuestionAnswerRepository.delete(object);
    }

}
