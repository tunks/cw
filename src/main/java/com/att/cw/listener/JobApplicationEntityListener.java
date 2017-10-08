/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.dto.mappers.BaseEntityMapper;
import com.att.cw.dto.mappers.JobApplicationDtoMapper;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.JobVacancy;
import com.att.cw.support.EntityHelper;
import com.att.cw.exception.JobApplicationException;
import com.att.cw.model.Participant;
import com.att.cw.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Job Application entity listener
 *
 * @author ebrimatunkara
 */
public class JobApplicationEntityListener extends BaseEntityListener{

    @PrePersist
    void onCreate(JobApplication entity) {
        //validate job application
        validate(entity);
    }

    @PreUpdate
    void beforePersist(JobApplication entity) {
        //submitted job application cannot be saved
//        if(entity.isSubmitted()){
//            throw new JobApplicationException("Submitted Job application cannot be saved");
//        }
        //validate job application
        validate(entity);
    }
    
     @PostPersist
     public void postPersist(JobApplication entity) {
          entity.getQuestionAnswers().stream().forEach(x->{
              if(x.getApplication() == null){
                x.setApplication(entity);
             }
         });
          
        //publish event message
        User user = entity.getCandidate().getUser();
        Set<Participant> recipients = Collections.singleton(new Participant(user.getEmail()));
        String subject = "Job application submitted";
        String content = "Your job application was successfully submitted";  
        publishEventMessage(subject, recipients, content);
     }
     
     public void postUpdateMessage(JobApplication entity) {
        //publish event message
        User user = entity.getCandidate().getUser();
        Set<Participant> recipients = Collections.singleton(new Participant(user.getEmail()));
        String subject = "Job application submitted";
        String content = "Your job application was successfully submitted on "+entity.getLastModifiedDate();  
        publishEventMessage(subject, recipients, content);
     }

    //validate job application 
    private void validate(JobApplication entity) throws JobApplicationException {
        //submitted job applications cannot be saved

        Job job = entity.getJob();
        //throw exception if job is not set
        if (job == null) {
            throw new JobApplicationException("Job application failed to save, job is not set!");
        }
        //get job vacancy
        JobVacancy vacancy = job.getVacancy();
        //throw exception if job vacancy is null
        if (vacancy == null) {
            throw new JobApplicationException("Job application failed to save, vacancy is null!");
        }
        //throw exepction if close date is overdue
        if (EntityHelper.isJobDateOverdue(vacancy)) {
            throw new JobApplicationException("Job application failed to save, application is pass overdue");
        }

        //get job required questions
        Set<JobQuestion> requiredQuestions = job.getQuestions().stream()
                .filter(q -> q.isRequired())
                .collect(Collectors.toSet());
        //get job application answers
        Set<JobQuestionAnswer> answers = entity.getQuestionAnswers();
        //get job application answer questions
        Set<JobQuestion> answerQuestions = answers.stream()
                .filter(a -> {
                    return (a.getQuestion() != null && a.getQuestion().isRequired());
                })
                .map(a -> {
                    return a.getQuestion();
                })
                .collect(Collectors.toSet());
        //valiate all required questions
        List<String> errors = new ArrayList();
        requiredQuestions
                .parallelStream()
                .forEach(q -> {
                    //throw exception if required question is not answered 
                    if (!answerQuestions.contains(q)) {
                        errors.add(q.getQuestion());
                    }
                });

        //throw exception is erros not empty
        if (!errors.isEmpty()) {
            throw new JobApplicationException("Job questions required: " + errors);

        }
    }

    @Override
    public BaseEntityMapper getEntityMapper() {
         return new JobApplicationDtoMapper();
    }

    @Override
    protected String[] getExcludedFields() {
        return ArrayUtils.EMPTY_STRING_ARRAY;
    }
}
