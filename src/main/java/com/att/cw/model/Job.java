package com.att.cw.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Job entity class
 *
 */
@Entity
@Table(name = "jobs")
public class Job extends Audit<Long>{
     private static final long serialVersionUID = 1L;
    /**
     * job id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * job title
     */
    @NotNull
    private String title;
    /**
     * job description
     */
    @NotNull
    private String description;
    /**
     * job opening start date
     */
    private Date openDate;
    /**
     * job deadline closing date
     */
    private Date closeDate;
    /*
     * department or group id  that owns the job
     */
    private Long ownerId;
    /**
     * job candidate applications
     */
    @OneToMany
    private Set<JobApplication> applications;
    /**
     * job posting work flow process
     */
    @OneToOne
    private JobWorkFlow workflow;

    public Job() {}

    public Job(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobApplication> applications) {
        this.applications = applications;
    }

    public JobWorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(JobWorkFlow workflow) {
        this.workflow = workflow;
    }
}
