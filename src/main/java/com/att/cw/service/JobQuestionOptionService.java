/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobQuestionOptionRepository;
import com.att.cw.model.QuestionOption;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("jobQuestionOptionService")
public class JobQuestionOptionService implements CrudService<QuestionOption, Long> {

    @Resource
    private JobQuestionOptionRepository jobQuestionOptionRepository;

    @Override
    public QuestionOption save(QuestionOption object) {
        return jobQuestionOptionRepository.save(object);
    }

    public Iterable<QuestionOption> save(Iterable<QuestionOption> objects) {
        return jobQuestionOptionRepository.save(objects);
    }

    @Override
    public QuestionOption find(Long id) {
        return jobQuestionOptionRepository.findOne(id);
    }

    @Override
    public List<QuestionOption> findAll() {
        return (List<QuestionOption>) jobQuestionOptionRepository.findAll();
    }

    @Override
    public Page<QuestionOption> findAll(Pageable page) {
        return jobQuestionOptionRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobQuestionOptionRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        return jobQuestionOptionRepository.exists(id);
    }

    @Override
    public void delete(QuestionOption object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
