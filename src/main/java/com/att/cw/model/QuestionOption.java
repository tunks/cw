/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    /**
     * Date pair for date range values
     */
    @OneToOne
    private QuestionOption pair;
    
    @ManyToMany(mappedBy="questionOptions")
    private Set<JobAnswerEntry>  answerEntries;  

    public QuestionOption() {
    }

    public QuestionOption(String value) {
        this.value = value;
    }

    public QuestionOption(Long id, String value) {
        this.id = id;
        this.value = value;
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

    public QuestionOption getPair() {
        return pair;
    }

    public void setPair(QuestionOption pair) {
        this.pair = pair;
    }

    public Set<JobAnswerEntry> getAnswerEntries() {
        return answerEntries;
    }

    public void setAnswerEntries(Set<JobAnswerEntry> answerEntries) {
        this.answerEntries = answerEntries;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.value);
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
        return Objects.equals(this.id, other.id);
    }
}
