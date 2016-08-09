/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 * Job Vacancies
 * @author ebrimatunkara
 */
@Embeddable
public class JobVacancy implements Serializable{
    /**
     * job opening start date
     */
    @Column(name="open_date", nullable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date openDate;
    /**
     * job deadline closing date
     */
    @Column(name="close_date", nullable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date closeDate;
    
    private boolean open=false;

    public JobVacancy() {
    }

    public JobVacancy(Date openDate, Date closeDate) {
        this.openDate = openDate;
        this.closeDate = closeDate;
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
}