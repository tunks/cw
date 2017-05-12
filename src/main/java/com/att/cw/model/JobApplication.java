package com.att.cw.model;

import com.att.cw.listener.JobApplicationEntityListener;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id",referencedColumnName = "id")
    private JobCandidate candidate;
    /**
     * JobApplication work flow process
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private JobWorkFlow workflow;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Basic(fetch = LAZY)
    private Job job;
    /**
     * Job question answers
     */
    @OneToMany(cascade={MERGE,REMOVE}, orphanRemoval=true,mappedBy="application")
    private Set<JobQuestionAnswer> questionAnswers = new HashSet();
    /**
     * Determine if application is submitted or not
     */
    @Column(columnDefinition = "bit default 0", nullable = false)
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

    @Override
    public String toString() {
        return "JobApplication{" + "id=" + id + ", candidate=" + candidate + ", workflow=" + workflow + ", job=" + job + ", questionAnswers=" + questionAnswers + ", submitted=" + submitted + '}';
    }

}
