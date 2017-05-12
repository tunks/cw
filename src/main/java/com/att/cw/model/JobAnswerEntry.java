/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobAnswerEntry entity model
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "JOB_ANSWER_ENTRY")
public class JobAnswerEntry extends Audit<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private QuestionOption questionOption;

    /**
     * For note or text answers
     */
    @Column(name = "entry")
    private String value;

    /**
     * For file attachments
     */
    @OneToOne
    @JoinColumn(name = "document_id")
    private FileDocument document;

    /**
     * For multiple or select choice answer
     */
    private Boolean checked = Boolean.FALSE;
    
    @OneToOne(orphanRemoval=true)
    private JobQuestionAnswer questionAnswer;

    public JobAnswerEntry() {
    }

    public JobAnswerEntry(String value) {
        this.value = value;
    }

    public JobAnswerEntry(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    public JobAnswerEntry(QuestionOption questionOption, String value) {
        this.questionOption = questionOption;
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FileDocument getDocument() {
        return document;
    }

    public void setDocument(FileDocument document) {
        this.document = document;
    }

    public QuestionOption getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean isChecked() {
        return checked;
    }

    public JobQuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(JobQuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
   
}
