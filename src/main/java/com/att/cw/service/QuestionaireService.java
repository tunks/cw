/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.QuestionaireRepository;
import com.att.cw.dto.QuestionaireDto;
import com.att.cw.model.Questionaire;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * QuestionaireService
 *
 * @author ebrimatunkara
 */
@Service("questionaireService")
public class QuestionaireService implements CrudService<Questionaire, Long> {

    @Resource
    private QuestionaireRepository questionaireRepository;

    @Override
    public Questionaire save(Questionaire object) {
        return questionaireRepository.save(object);
    }

    @Override
    public Questionaire find(Long id) {
        return questionaireRepository.findOne(id);
    }

    @Override
    public List<Questionaire> findAll() {
        return (List<Questionaire>) questionaireRepository.findAll();
    }

    @Override
    public Page<Questionaire> findAll(Pageable page) {
        return questionaireRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        questionaireRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        questionaireRepository.deleteAll();
    }

    public Questionaire save(QuestionaireDto dto) {
        Questionaire entity;
        if (dto.getId() != null) {
            entity = questionaireRepository.findOne(dto.getId());
        } else {
            entity = new Questionaire();
        }
        //map the entity 
        entity.setName(dto.getName());
        entity.setRank(dto.getRank());
        return questionaireRepository.save(entity);
    }

    @Override
    public void delete(Questionaire questionaire) {
        questionaireRepository.delete(questionaire);
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
