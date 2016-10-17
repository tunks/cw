/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JobQuestionOption
 * @author ebrimatunkara
 */
@Entity
@Table(name="QUESTION_OPTION")
public class QuestionOption extends Audit<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    @Column(name="question_note")
    private String note;
  
    @ManyToOne(fetch = FetchType.LAZY)
    private  JobQuestion question;

    public QuestionOption() {
    }

    public QuestionOption(String note) {
        this.note = note;
    }

    public QuestionOption(String note, JobQuestion question) {
        this.note = note;
        this.question = question;
    }

    
    @Override
    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public JobQuestion getQuestion() {
        return question;
    }

    public void setQuestion(JobQuestion question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.note);
        hash = 31 * hash + Objects.hashCode(this.question);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionOption other = (QuestionOption) obj;
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        return true;
    }
    
    
}
