package com.att.cw.model;

import com.att.cw.listener.JobApplicationEntityListener;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@EntityListeners(JobApplicationEntityListener.class)
@Table(name = "JOB_APPLICATION")
public class JobApplication  extends Audit<Long> {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateCreated;
    private Date dateModified;
    /**
     * JobApplication candidate
     */
    @OneToOne
    @JoinColumn(name="candidate_id")
    private JobCandidate candidate;
    /**
     * JobApplication work flow process
     */
    @OneToOne
    @JoinColumn(name="workflow_id")
    private JobWorkFlow workflow;
  
    @ManyToOne
    @JoinColumn(name="job_id")
    @Basic(fetch=LAZY)
    private Job job;
    /**
     * Job resume 
     */
    
    @ManyToOne
    @JoinColumn(name="resume_id")
    private Resume resume;

    public JobApplication() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
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

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
