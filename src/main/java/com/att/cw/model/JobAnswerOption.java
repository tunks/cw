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
 * JobAnswerOption entity model
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "ANSWER_OPTION")
public class JobAnswerOption extends Audit<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private QuestionOption questionOption;

    /**
     * For note or text answers
     */
    @Column(name = "answer_note")
    private String note;

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

    public JobAnswerOption() {
    }

    public JobAnswerOption(String note) {
        this.note = note;
    }

    public JobAnswerOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    public JobAnswerOption(QuestionOption questionOption, String note) {
        this.questionOption = questionOption;
        this.note = note;
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
}
