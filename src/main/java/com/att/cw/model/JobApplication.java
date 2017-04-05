package com.att.cw.model;

import com.att.cw.listener.JobApplicationEntityListener;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@EntityListeners(JobApplicationEntityListener.class)
@Table(name = "JOB_APPLICATION")
public class JobApplication extends Audit<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * JobApplication candidate
     */
    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private JobCandidate candidate;
    /**
     * JobApplication work flow process
     */
    @OneToOne
    @JoinColumn(name = "workflow_id")
    private JobWorkFlow workflow;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @Basic(fetch = LAZY)
    private Job job;
    /**
     * Job question answers
     */
    @OneToMany
    private Set<JobQuestionAnswer> questionAnswers = new HashSet();
    /**
     * Determine if application is submitted or not
     */
    private Boolean submitted = Boolean.FALSE;

    public JobApplication() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(JobCandidate candidate) {
        this.candidate = candidate;
    }

    public JobWorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(JobWorkFlow workflow) {
        this.workflow = workflow;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Set<JobQuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Set<JobQuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Boolean isSubmitted() {
        return submitted;
    }
}
