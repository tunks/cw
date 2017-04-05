/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Standard Job workflow model
 *
 * @author ebrimatunkara
 */
@Table(name = "STANDARD_JOB_WORKFLOW")
@Entity
public class StandardJobWorkFlow extends JobWorkFlow {

    @Column(name = "workflow_name", nullable = false, unique = true)
    private String name;

    public StandardJobWorkFlow() {
        super();
    }

    public StandardJobWorkFlow(String name) {
        super();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
