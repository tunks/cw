/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.QuestionTypeRepository;
import com.att.cw.model.QuestionType;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * QuestionTypeService
 *
 * @author ebrimatunkara
 */
@Service("questionTypeService")
public class QuestionTypeService implements CrudService<QuestionType, Long> {

    @Resource
    private QuestionTypeRepository questionTypeRepository;

    @Override
    public QuestionType save(QuestionType object) {
        return questionTypeRepository.save(object);
    }

    @Override
    public QuestionType find(Long id) {
        return questionTypeRepository.findOne(id);
    }

    @Override
    public List<QuestionType> findAll() {
        return (List<QuestionType>) questionTypeRepository.findAll();
    }

    @Override
    public Page<QuestionType> findAll(Pageable page) {
        return questionTypeRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        questionTypeRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        questionTypeRepository.deleteAll();
    }

    public QuestionType findByName(String name) {
        return questionTypeRepository.findByName(name);
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(QuestionType object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
