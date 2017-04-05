/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.Job;
import com.att.cw.model.JobStatus;
import com.att.cw.model.JobVacancy;
import com.att.cw.support.EntityHelper;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Job entity listener
 *
 * @author ebrimatunkara
 */
public class JobEntityListener {

    @PrePersist
    void onCreate(Job entity) {
        validate(entity);
        //set job status  to OPEN (default) if null
        setJobStatus(entity);
    }

    private void setJobStatus(Job entity) {
        //set status to open(default) if it is null
        if (entity.getVacancy().getStatus() == null) {
            entity.getVacancy().setStatus(JobStatus.OPEN);
        }
    }

    @PreUpdate
    void onPersist(Job entity) {
        validate(entity);
        //set job status if null
        setJobStatus(entity);
    }

    //validate job object
    private void validate(Job entity) {
        //get job vacancy
        JobVacancy vacancy = entity.getVacancy();
        //throw exepction if close date is overdue
        EntityHelper.validateJobVacancy(vacancy);
    }
}
