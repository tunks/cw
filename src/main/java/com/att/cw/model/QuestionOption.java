/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobQuestionOption
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "QUESTION_OPTION")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question_value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    private JobQuestion question;

    /**
     * Date pair for date range values
     */
    @OneToOne
    private QuestionOption pair;

    public QuestionOption() {
    }

    public QuestionOption(String value) {
        this.value = value;
    }

    public QuestionOption(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public QuestionOption(String value, JobQuestion question) {
        this(value);
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JobQuestion getQuestion() {
        return question;
    }

    public void setQuestion(JobQuestion question) {
        this.question = question;
    }

    public QuestionOption getPair() {
        return pair;
    }

    public void setPair(QuestionOption pair) {
        this.pair = pair;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.value);
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
        if (!Objects.equals(this.value, other.value)) {
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
