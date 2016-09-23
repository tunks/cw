/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.JobQuestion;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.att.cw.dao.JobQuestionRepository;

/**
 * JobQuestion Service -- manages job components
 * @author ebrimatunkara
 */
@Service("jobQuestionService")
public class JobQuestionService implements CrudService<JobQuestion,Long>{
    @Resource
    private JobQuestionRepository jobComponentRepository;
    
    @Override
    public JobQuestion save(JobQuestion object) {
        return jobComponentRepository.save(object);
    }

    @Override
    public JobQuestion find(Long id) {
       return jobComponentRepository.findOne(id);
    }

    @Override
    public List<JobQuestion> findAll() {
       return (List<JobQuestion>) jobComponentRepository.findAll();
    }

    @Override
    public Page<JobQuestion> findAll(Pageable page) {
        return jobComponentRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
       jobComponentRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
