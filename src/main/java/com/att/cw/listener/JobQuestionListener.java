/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.JobQuestion;
import com.att.cw.service.JobQuestionService;
import java.util.List;
import java.util.UUID;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author ebrimatunkara
 */
public class JobQuestionListener {
    @Autowired
    private JobQuestionService jobQuestionService;
    
    @PrePersist
    void beforeCreate(JobQuestion entity) {
        //set boolean value
        Boolean required = entity.getRequired();
        required = (required == null) ? Boolean.FALSE : required;
        entity.setRequired(required);
        //check if reference number is num
        setReferenceNumber(entity);
        //set question type is null
        if (entity.getQuestionType() == null) {

        }
        //check if question category is 
        if (entity.getCategory() == null) {
            //throw new JobException("Job question category must be set!!");
        }
        
        //if(entity.)
    }

    @PostUpdate
    void postUpdate(JobQuestion entity){
        Long associatedId = entity.getAssociatedId();
        if(associatedId == null){
           //TODO
//           SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//           if( jobQuestionService != null && entity.getId() != null ){
//               List<JobQuestion> questions  = jobQuestionService.findByAssociatedId(entity.getId());
//               questions.stream().forEach(q->{
//                   if(!q.getId().equals(entity.getId())){
//                       q.setQuestion(entity.getQuestion());
//                       q.setRank(entity.getRank());
//                       jobQuestionService.save(q);
//                   }
//               });
           //}
        }
    }
    /**
     * Check if reference number is num
     */
    private void setReferenceNumber(JobQuestion entity) {
        if (entity.getReferenceNumber() == null) {
            entity.setReferenceNumber(UUID.randomUUID().toString());
        }
    }
}
