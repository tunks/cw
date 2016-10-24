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
import com.att.cw.model.QuestionType;
import com.att.cw.model.Questionaire;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JobQuestion Service -- manages job components
 * @author ebrimatunkara
 */
@Service("jobQuestionService")
public class JobQuestionService implements CrudService<JobQuestion,Long>{
    @Resource
    private JobQuestionRepository jobComponentRepository;
    
    @Autowired
    private QuestionTypeService questionTypeService;
    
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

    public void delete(JobQuestion entity) {
       jobComponentRepository.delete(entity);
    }
    
    public Set<JobQuestion> saveDto( Questionaire questionaire, Set<JobQuestionDto> questions) {   
       Set<JobQuestion> collection = questions.stream().map(q ->{
            return mapEntity(questionaire,q);
        }).collect(toSet());
       //TODO -- refactor
       Set result =  new HashSet();
       Iterator<JobQuestion> itr = jobComponentRepository.save(collection)
                                                         .iterator();
       while(itr.hasNext()){
          result.add(itr.next());
       }
       
      return result;
    }

    private JobQuestion mapEntity(Questionaire questionaire, JobQuestionDto questionDto) {
        JobQuestion question = (questionDto.getId() != null)? jobComponentRepository.findOne(questionDto.getId()): new JobQuestion();
        if(question == null){
            question = new JobQuestion();
            question.setQuestionaire(questionaire);
         }
        //set question type if null or different
        if(question.getQuestionType()  == null || 
          (question.getQuestionType().getId().equals(questionDto.getId())))
          {
            QuestionType questionType = questionTypeService.find(questionDto.getQuestionType().getId());
            question.setQuestionType(questionType);
          }
       
        question.setQuestion(questionDto.getQuestion());
        question.setRequired(questionDto.isRequired());
        return question;
    }

    public JobQuestion saveDto(Questionaire questionaire, JobQuestionDto question) {
          JobQuestion entity = mapEntity(questionaire,question);
          return jobComponentRepository.save(entity);
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
