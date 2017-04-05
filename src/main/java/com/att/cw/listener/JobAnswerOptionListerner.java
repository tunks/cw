/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.JobAnswerOption;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * JobAwsnerOption listener
 *
 * @author ebrimatunkara
 */
public class JobAnswerOptionListerner {

    @PrePersist
    void onCreate(JobAnswerOption entity) {
        //get job question
        //JobQuestion question = entity.getQuestion();
        //validate(entity, question);
    }

    @PreUpdate
    void onPersist(JobAnswerOption entity) {
        //get job question

    }

    //validate the entity
//    private void validate(JobQuestionAnswer entity, JobQuestion question) throws JobApplicationException {
//        //raise exception if question is required and no answers
//        if (entity.getAnswers().isEmpty() && question.isRequired()) {
//            throw new JobApplicationException("Question must be answer!!");
//        }
//    }
}
