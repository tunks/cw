
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.QuestionDto;
import com.att.cw.dto.QuestionaireDto;
import com.att.cw.dto.mappers.QuestionaireDtoMapper;
import com.att.cw.exception.NotFoundException;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.Questionaire;
import com.att.cw.service.JobQuestionOptionService;
import com.att.cw.service.JobQuestionService;
import com.att.cw.service.QuestionaireService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Questionaire controller
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/questionaires")
public class  QuestionaireController implements BaseController<QuestionaireDto,Long>{
    /**
     * QuestionaireService
     */
    @Autowired
    private QuestionaireService questionaireService;
    
    @Autowired
    private JobQuestionService jobQuestionService;
    
    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;
    /**
     * Find all job questionaires
     * @param page
     * @return , list of all job questionaires
     */
    @RequestMapping( method = RequestMethod.GET)
    public Page<QuestionaireDto> findAll(Pageable page) {
         return QuestionaireDtoMapper.mapEntityPageIntoDTOPage(page,questionaireService.findAll(page));                                    
    }
    /**
     * Find questionaire by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public QuestionaireDto find(@PathVariable Long id) {
      return QuestionaireDtoMapper.mapEntityIntoDTO(questionaireService.find(id));
    }
    /**
     * Delete all questionaires
     */ 
    @RequestMapping(method = RequestMethod.DELETE)
    @Override
    public void deleteAll() {
        questionaireService.deleteAll();
    }

    /**
     * Delete questionaire by id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(Long id) {
       questionaireService.delete(id);
    }

    /**
     * Create questionaire
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public QuestionaireDto create(@RequestBody QuestionaireDto object) {
       return QuestionaireDtoMapper.mapEntityIntoDTO(questionaireService.save(object));
    }
    
    /**
     * Update questionaire
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public QuestionaireDto update(@RequestBody QuestionaireDto object) {
       return QuestionaireDtoMapper.mapEntityIntoDTO(questionaireService.save(object));
    }
    
    /**
     * Questionaire question urls
     */
    
    /**
     * Save questions
     * @param id
     * @param questions
     * @return 
     */
    @RequestMapping(value = "/{id}/questions", method = RequestMethod.POST)
    public Set<QuestionDto> saveQuestions(@PathVariable Long id, @RequestBody Set<QuestionDto> questions) {
      Questionaire questionaire = questionaireService.find(id);
      if(questionaire != null){
         Set<JobQuestion> items = jobQuestionService.saveDto(questionaire,questions);
         questionaire.getQuestions().addAll(items);
         questionaire = questionaireService.save(questionaire);
         return QuestionaireDtoMapper.mapEntityIntoDTO(questionaire).getQuestions();
      }
      else{
          //throw exception
          throw new NotFoundException(id); 
      }
    }
    
}
