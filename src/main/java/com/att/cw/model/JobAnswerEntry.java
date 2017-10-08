/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @ManyToMany(fetch=EAGER) //cascade={MERGE})
    @JoinTable(
            name = "JOB_QUESTION_ANSWER_ENTRY_OPTION",
            joinColumns = {
                @JoinColumn(name = "ANSWER_ENTRY_ID", nullable = false,updatable = false, referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "QUESTION_OPTON_ID", nullable = false, referencedColumnName = "ID")})
    private Set<QuestionOption> questionOptions = new HashSet();

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
    
    @OneToOne
    private JobQuestionAnswer questionAnswer;

    public JobAnswerEntry() {
    }

    public JobAnswerEntry(JobQuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public JobAnswerEntry(String value) {
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

    public Set<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(Set<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
