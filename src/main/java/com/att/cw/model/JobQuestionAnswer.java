/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import com.att.cw.listener.JobAnswerListener;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobQuestionAnswer entity model This model stores the answers of the job
 * application question
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "JOB_ANSWER")
@EntityListeners(JobAnswerListener.class)
public class JobQuestionAnswer extends Component {

    @OneToOne(cascade = {MERGE, REMOVE}, orphanRemoval = true, mappedBy="questionAnswer")
    private JobAnswerEntry answerEntry;

    @ManyToOne(cascade = {MERGE, REMOVE})
    private JobQuestion question;
    
    @ManyToOne(cascade={MERGE})
    private JobApplication application;

    public JobQuestionAnswer() {
    }

    public JobQuestionAnswer(JobQuestion question) {
        this.question = question;
    }

    public JobQuestion getQuestion() {
        return question;
    }

    public void setQuestion(JobQuestion question) {
        this.question = question;
    }

    public JobAnswerEntry getAnswerEntry() {
        return answerEntry;
    }

    public void setAnswerEntry(JobAnswerEntry answerEntry) {
        this.answerEntry = answerEntry;
    }

    public JobApplication getApplication() {
        return application;
    }

    public void setApplication(JobApplication application) {
        this.application = application;
    }
}
