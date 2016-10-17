/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import com.att.cw.listener.JobComponentListener;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobQuestion Entity model
 * This model stores the question of the job application 
 * @author ebrimatunkara
 */
@Entity
@Table(name="JOB_QUESTION")
@EntityListeners(JobComponentListener.class)
public class JobQuestion extends Component {  
    @Column(nullable=false)
    private String question;
    
    @Enumerated(EnumType.STRING)
    private Relevance relevance = Relevance.MEDIUM;
    
    /**
     * Question category 
     */
    @ManyToOne
    //@JoinColumn(nullable = false)
    private QuestionCategory category;
     
    @Column(name="question_type" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionOptionType questionType;
    
    /**
     * Question option
     */
    @OneToMany(fetch = FetchType.LAZY)
    private Set<QuestionOption> options = new HashSet();
    
    
    /**
     * Question reference number
     */
    @Column(name="reference_number", nullable=false)
    private String referenceNumber;
    /**
     * Variable to determine if question answer is required or not
     */
    private Boolean required = Boolean.FALSE;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Questionaire questionaire;
    

    public JobQuestion() {
    }
    
    public JobQuestion(String question) {
        this.question = question;
    }

    public JobQuestion(String question, QuestionOptionType questionType) {
        this.question = question;
        this.questionType = questionType;
    }

    public JobQuestion(String question, QuestionCategory category, QuestionOptionType questionType) {
        this.question = question;
        this.category = category;
        this.questionType = questionType;
    }

    
    public JobQuestion(String question, Set<QuestionOption> options) {
        this.question = question;
        this.options = options;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Relevance getRelevance() {
        return relevance;
    }

    public void setRelevance(Relevance relevance) {
        this.relevance = relevance;
    }

    public Set<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(Set<QuestionOption> options) {
        this.options = options;
    }
    
    public void addOptions(QuestionOption option){
        this.options.add(option);
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
    
    public Boolean isRequired(){
       return required;
    } 

    public QuestionOptionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionOptionType questionType) {
        this.questionType = questionType;
    }  

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    public Questionaire getQuestionaire() {
        return questionaire;
    }

    public void setQuestionaire(Questionaire questionaire) {
        this.questionaire = questionaire;
    }
}
