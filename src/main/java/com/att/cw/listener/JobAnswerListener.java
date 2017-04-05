/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.JobQuestion;
import com.att.cw.exception.JobApplicationException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Job answer Listener
 *
 * @author ebrimatunkara
 */
public class JobAnswerListener {

    @PrePersist
    void onCreate(JobQuestionAnswer entity) {
        //get job question
        JobQuestion question = entity.getQuestion();
        validate(entity, question);
    }

    @PreUpdate
    void onPersist(JobQuestionAnswer entity) {
        //get job question
        JobQuestion question = entity.getQuestion();
        validate(entity, question);
    }

    //validate the entity
    private void validate(JobQuestionAnswer entity, JobQuestion question) throws JobApplicationException {
        //raise exception if question is required and no answers
        if (question == null) {
            throw new JobApplicationException("Question not available!!");
        }

        //if(question.)
        if (entity.getAnswerOptions().isEmpty() && question.isRequired()) {
            throw new JobApplicationException("Question must be answer!!");
        }
    }
}
