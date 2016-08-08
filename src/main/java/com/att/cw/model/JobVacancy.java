/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.sql.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Job Vacancies
 * @author ebrimatunkara
 */
@Entity
@Table(name="JOB_VACANCY")
public class JobVacancy extends Audit<Long>{
    /**
     * job positing  id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * job opening start date
     */
    private Date openDate;
    /**
     * job deadline closing date
     */
    private Date closeDate;
    
//    @ManyToOne
//    private Job job;
    /**
     * job posting work flow process
     */
    @OneToOne
    private JobWorkFlow workflow;
        /**
     * job candidate applications
     */
    @OneToMany
    private Set<JobApplication> applications;
    
    @Override
    public Long getId() {
       return id;
    }
    
    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
//
//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
//     
    public Set<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobApplication> applications) {
        this.applications = applications;
    }

    public JobWorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(JobWorkFlow workflow) {
        this.workflow = workflow;
    }
}
