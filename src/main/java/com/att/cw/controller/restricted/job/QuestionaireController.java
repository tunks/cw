
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.dto.QuestionaireDto;
import com.att.cw.dto.mappers.JobQuestionDtoMapper;
import com.att.cw.dto.mappers.QuestionaireDtoMapper;
import com.att.cw.exception.NotFoundException;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.Questionaire;
import com.att.cw.service.JobQuestionOptionService;
import com.att.cw.service.JobQuestionService;
import com.att.cw.service.QuestionaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Questionaire controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/questionaires")
public class QuestionaireController implements BaseController<QuestionaireDto, Long> {

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
     *
     * @param page
     * @return , list of all job questionaires
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<QuestionaireDto> findAll(Pageable page) {
        return QuestionaireDtoMapper.mapEntityPageIntoDTOPage(page, questionaireService.findAll(page));
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
    public void delete(@PathVariable Long id) {
        Questionaire questionaire = questionaireService.find(id);
        if (questionaire != null) {
            //clear questions
            questionaire.getQuestions().clear();
            //delete questionaire
            questionaireService.delete(questionaire);
        } else {   //throw exception
            throw new NotFoundException(id);
        }

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
     * Create and save questionaire question questions
     *
     * @param id
     * @param question
     * @return
     */
    @RequestMapping(value = "/{id}/questions", method = RequestMethod.POST)
    public JobQuestionDto saveQuestion(@PathVariable Long id, @RequestBody JobQuestionDto question) {
        return saveQuestionaireQuestion(id, question, true);
    }

    private JobQuestionDto saveQuestionaireQuestion(Long id, JobQuestionDto question, boolean isNew) throws NotFoundException {
        Questionaire questionaire = questionaireService.find(id);
        if (questionaire != null) { 
            JobQuestion item = jobQuestionService.saveDto(question, isNew ? questionaire : null);
            return JobQuestionDtoMapper.mapEntityIntoDTO(item);
        } else {
            //throw exception
            throw new NotFoundException(id);
        }
    }

    /**
     * Update and save questionaire question
     *
     * @param id
     * @param question
     * @return
     */
    @RequestMapping(value = "/{id}/questions", method = RequestMethod.PUT)
    public JobQuestionDto updateQuestion(@PathVariable Long id, @RequestBody JobQuestionDto question) {
        return saveQuestionaireQuestion(id, question, false);
    }

    /**
     * Delete question
     *
     * @param id
     * @param questionId
     * @return
     */
    @RequestMapping(value = "/{id}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestions(@PathVariable Long id, @PathVariable Long questionId) {
        Questionaire questionaire = questionaireService.find(id);
        if (questionaire != null) {
            JobQuestion question = jobQuestionService.find(questionId);
            if (question != null) {
                //clear question options
                question.getOptions().clear();
                //remove question from questionaire
                questionaire.getQuestions().remove(question);
                questionaireService.save(questionaire);
                jobQuestionService.delete(question);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                //throw exception
                throw new NotFoundException(questionId);
            }
        } else {
            //throw exception
            throw new NotFoundException(id);
        }
    }

}
