/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.JobQuestion;
import java.util.UUID;
import javax.persistence.PrePersist;

/**
 *
 * @author ebrimatunkara
 */
public class JobComponentListener {

    @PrePersist
    void onCreate(JobQuestion entity) {
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
