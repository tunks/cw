/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.JobQuestionListDto;
import com.att.cw.dto.QuestionCategoryDto;
import com.att.cw.dto.QuestionOptionDto;
import com.att.cw.dto.mappers.QuestionCategoryDtoMapper;
import com.att.cw.dto.mappers.QuestionOptionDtoMapper;
import com.att.cw.exception.NotFoundException;
import com.att.cw.model.Job;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.QuestionOption;
import com.att.cw.service.JobQuestionOptionService;
import com.att.cw.service.JobQuestionService;
import com.att.cw.service.JobService;
import com.att.cw.service.QuestionCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuestionOptionController
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/questions")
public class JobQuestionController implements BaseController<JobQuestion, Long> {

    @Autowired
    private JobQuestionService jobQuestionService;

    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    private JobService jobService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * Find Job question
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JobQuestion find(@PathVariable Long id) {
        return jobQuestionService.find(id);
    }

    /**
     * Create Job question
     *
     * @param object
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public JobQuestion create(JobQuestion object) {
        return jobQuestionService.save(object);
    }

    /**
     * Update Job question
     *
     * @param object
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public JobQuestion update(JobQuestion object) {
        return jobQuestionService.save(object);
    }

    @Override
    public void deleteAll() {
        jobQuestionService.deleteAll();
    }

    /**
     * Find all jobs by question id and user id
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public JobQuestionListDto findAll(@RequestParam("jobid") Long id, @RequestParam("userId") Long userId) {
        Job job = jobService.find(id);
        if (job != null) {
            return JobQuestionListDto.build(job);
        }
        throw new NotFoundException(id);
    }

    /**
     * Delete Job question
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable Long id) {
        Job job = jobService.findByQuestionId(id);//jobService  (id);
        JobQuestion question = find(id);
        if (job != null) {
            job.getQuestions().remove(question);

            jobService.save(job);
        }
        jobQuestionService.delete(question);
    }

    /**
     * Create new JobQuestion option
     *
     * @param id
     * @param optionDto
     * @return
     */
    @RequestMapping(value = "/{id}/options", method = RequestMethod.POST)
    public QuestionOptionDto createQuestionOption(@PathVariable Long id, @RequestBody QuestionOptionDto optionDto) {
        JobQuestion question = jobQuestionService.find(id);
        if (question != null) {
            QuestionOption entity = QuestionOptionDtoMapper.mapDtoIntoEntity(optionDto);
            //set question entity and save option entity
            entity.setQuestion(question);
            entity = jobQuestionOptionService.save(entity);
            //append question option entity to question
            question.getOptions().add(entity);
            //save question entity
            jobQuestionService.save(question);
            return QuestionOptionDtoMapper.mapEntityIntoDTO(entity);
        } else {
            throw new NotFoundException(id);
        }
    }

    /**
     * Update existing JobQuestion option
     *
     * @param id
     * @param optionDto
     * @return
     */
    @RequestMapping(value = "/{id}/options", method = RequestMethod.PUT)
    public QuestionOptionDto updateQuestionOption(@PathVariable Long id, @RequestBody QuestionOptionDto optionDto) {
        QuestionOption entity = jobQuestionOptionService.find(optionDto.getId());
        if (jobQuestionService.exists(id) && entity != null) {
            entity.setValue(optionDto.getValue());
            return QuestionOptionDtoMapper.mapEntityIntoDTO(jobQuestionOptionService.save(entity));
        } else {
            throw new NotFoundException("Either question  or option entity not found");
        }
    }

    /**
     * Get all questions category
     *
     * @return
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<QuestionCategoryDto> getQuestionCategories() {
        return QuestionCategoryDtoMapper.mapEntitiesIntoDTOs(questionCategoryService.findAll());
    }
}
