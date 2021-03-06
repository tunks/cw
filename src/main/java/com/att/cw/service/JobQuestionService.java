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
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.dto.QuestionCategoryDto;
import com.att.cw.dto.QuestionOptionDto;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionType;
import com.att.cw.model.Questionaire;
import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JobQuestion Service -- manages job components
 *
 * @author ebrimatunkara
 */
@Service("jobQuestionService")
public class JobQuestionService implements CrudService<JobQuestion, Long> {

    @Resource
    private JobQuestionRepository jobQuestionRepository;

    @Autowired
    private QuestionTypeService questionTypeService;
    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private RankService rankService;
    
    @Override
    public JobQuestion save(JobQuestion object) {
        return jobQuestionRepository.save(object);
    }

    @Override
    public JobQuestion find(Long id) {
        return jobQuestionRepository.findOne(id);
    }

    @Override
    public List<JobQuestion> findAll() {
        return (List<JobQuestion>) jobQuestionRepository.findAll();
    }

    @Override
    public Page<JobQuestion> findAll(Pageable page) {
        return jobQuestionRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobQuestionRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(JobQuestion entity) {
        jobQuestionRepository.delete(entity);
    }

    public Set<JobQuestion> saveDto(Set<JobQuestionDto> questions) {
        Set<JobQuestion> result = questions.stream().map(q -> {
            return saveDto(q);
        }).collect(toSet());

        return result;
    }

    private JobQuestion mapEntity(JobQuestionDto questionDto) {
        Long jobId = questionDto.getId();
        JobQuestion entity = (jobId != null) ? jobQuestionRepository.findOne(jobId) : new JobQuestion();

        //generate reference number if it is not defined
        if (entity.getReferenceNumber() == null) {
            entity.setReferenceNumber(RandomStringUtils.randomAlphabetic(6));
        }
        entity.setAssociatedId(questionDto.getAssociatedId());
        //save question type
        saveQuestionType(questionDto, entity);

        //save question category
        saveQuestionCategory(questionDto.getCategory(), entity);
         //set rank
         //rankService.
        //set question type
        entity.setQuestion(questionDto.getQuestion());
        entity.setRequired(questionDto.isRequired());
        entity.setRank(questionDto.getRank());
        return entity;
    }

    private void saveQuestionType(JobQuestionDto questionDto, JobQuestion entity) {
        //set question type if null or different
        if (questionDto.getQuestionType() != null && questionDto.getQuestionType().getId() != null) {
            QuestionType questionType = questionTypeService.find(questionDto.getQuestionType().getId());
            entity.setQuestionType(questionType);
        }
    }

    private void saveQuestionCategory(QuestionCategoryDto categoryDto, JobQuestion entity) {
        //set question type if null or different
        if (categoryDto != null) {
            QuestionCategory category = questionCategoryService.find(categoryDto.getId());
            entity.setCategory(category);
        }
    }

    private Set<QuestionOption> saveQuestionOptions(Set<QuestionOptionDto> optionsDto) {
        //set question opetions
        Set<QuestionOption> options = new HashSet();
        optionsDto.stream()
                .forEach(op -> {
                    Long id = op.getId();
                    QuestionOption option = (id != null) ? jobQuestionOptionService.find(id) : new QuestionOption();
                    option.setValue(op.getValue());
                    if (id != null) {
                        jobQuestionOptionService.save(option);
                    } else {
                        options.add(option);
                    }
                });
        return options;
    }

    public JobQuestion saveDto(JobQuestionDto questionDto) {
        JobQuestion entity = updateQuestionDto(questionDto);
        return jobQuestionRepository.save(entity);
    }

    public JobQuestion saveDto(JobQuestionDto questionDto, Questionaire questionaire) {
        JobQuestion entity = updateQuestionDto(questionDto);
        if (questionaire != null) {
            entity.setQuestionaire(questionaire);
        }
        return jobQuestionRepository.save(entity);
    }

    private JobQuestion updateQuestionDto(JobQuestionDto questionDto) {
        JobQuestion entity = mapEntity(questionDto);
        //save question options
        if (entity.getId() == null) {
            entity = jobQuestionRepository.save(entity);
        }
        Set<QuestionOption> options = saveQuestionOptions(questionDto.getOptions());
        if (options.size() > 0) {
            entity.getOptions().addAll(options);
        }
        return entity;
    }

    @Override
    public boolean exists(Long id) {
        return jobQuestionRepository.exists(id);
    }

    public JobQuestion save(JobQuestionDto dto) {
           JobQuestion question = find(dto.getId());
           if(question == null){
               question  = new JobQuestion();
            }
              question.setRequired(dto.isRequired());
              question.setQuestion(dto.getQuestion());
              question.setAssociatedId(dto.getAssociatedId());
              question.setRank(dto.getRank());
              //set question type
              QuestionType qType = questionTypeService.find(dto.getQuestionType().getId());
              question.setQuestionType(qType);
              //set question category
              QuestionCategory qCategory = questionCategoryService.find(dto.getCategory().getId());
              question.setCategory(qCategory);
              //set question options
              question.setOptions(dto.getOptions().stream().map(x->{
                   Long id = x.getId();
                   QuestionOption option  = jobQuestionOptionService.find(id);
                   if(option == null){
                     option = new QuestionOption();
                    }
                   option.setValue(x.getValue());
                 return option;
              }).collect(toSet()));
               
           return save(question);
    }
    
    public List<JobQuestion> findByAssociatedId(Long associatedId){
         return jobQuestionRepository.findByAssociatedId(associatedId);
    }

}
