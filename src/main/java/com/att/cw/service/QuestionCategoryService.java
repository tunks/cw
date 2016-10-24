/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.QuestionCategoryRepository;
import com.att.cw.model.QuestionCategory;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * QuestionCategoryService
 * @author ebrimatunkara
 */
@Service("questionCategoryService")
public class QuestionCategoryService implements CrudService<QuestionCategory,Long>{
    @Resource
    private QuestionCategoryRepository questionCategoryRespository;
    @Override
    public QuestionCategory save(QuestionCategory object) {
        return questionCategoryRespository.save(object);
    }

    @Override
    public QuestionCategory find(Long id) {
      return questionCategoryRespository.findOne(id);
    }

    @Override
    public List<QuestionCategory> findAll() {
       return (List<QuestionCategory>) questionCategoryRespository.findAll();
    }

    @Override
    public Page<QuestionCategory> findAll(Pageable page) {
         return questionCategoryRespository.findAll(page);
    }
    
    public QuestionCategory findByCategory(String category){
       return questionCategoryRespository.findByCategory(category);
    }

    @Override
    public void delete(Long id) {
          questionCategoryRespository.delete(id);
    }

    @Override
    public void deleteAll() {
        questionCategoryRespository.deleteAll();
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
