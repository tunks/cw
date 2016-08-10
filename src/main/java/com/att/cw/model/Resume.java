/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User candidate resume
 * @author ebrimatunkara
 */
@Entity
@Table(name="RESUME")
public class Resume extends Audit<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private FileDocument document;
    
    @Override
    public Long getId() {
       return id;
    }

    public FileDocument getDocument() {
        return document;
    }

    public void setDocument(FileDocument document) {
        this.document = document;
    }
}
