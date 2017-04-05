/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import com.att.cw.listener.JobAnswerListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany
    private Set<JobAnswerOption> answerOptions = new HashSet();

    @ManyToOne
    private JobQuestion question;

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

    public Set<JobAnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<JobAnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public void addAnswerOption(JobAnswerOption answerOption) {
        this.answerOptions.add(answerOption);
    }
}
