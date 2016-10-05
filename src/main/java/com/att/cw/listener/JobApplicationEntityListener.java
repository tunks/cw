/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobVacancy;
import com.att.cw.support.EntityHelper;
import com.att.cw.support.JobApplicationException;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Job Application entity listener
 * @author ebrimatunkara
 */
public class JobApplicationEntityListener {
    @PrePersist
    void onCreate(JobApplication entity) {
        //validate job application
        validate(entity);   
    }

    @PreUpdate
    void onPersist(JobApplication entity) {
       //validate job application
        validate(entity);  
    }
    
    //validate job application 
    private void validate(JobApplication entity) throws JobApplicationException {
        Job job = entity.getJob();
        //throw exception if job is not set
        if(job == null){
            throw new JobApplicationException("Job application failed to save, job is not set!");
        }
        //get job vacancy
        JobVacancy vacancy = job.getVacancy();
        //throw exception if job vacancy is null
        if(vacancy == null){
            throw new JobApplicationException("Job application failed to save, vacancy is null!");
        }
        //throw exepction if close date is overdue
        if (EntityHelper.isJobDateOverdue(vacancy)){
            throw new JobApplicationException("Job application failed to save, application is pass overdue");
        }
    }

}
