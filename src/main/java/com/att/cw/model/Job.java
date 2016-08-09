package com.att.cw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Job entity class
 */
@Entity
@Table(name = "JOB")
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


    @OneToOne
    private JobWorkFlow workflow;
    /**
     * job candidate applications
     */
    @OneToMany
    private Set<JobApplication> applications;
    
    @Embedded
    private JobVacancy vacancy;
    /**
     * department or group that owns the job
     */
//    @OneToOne
//    private Group ownerGroup;

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

    public JobVacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(JobVacancy vacancy) {
        this.vacancy = vacancy;
    }

    public JobWorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(JobWorkFlow workflow) {
        this.workflow = workflow;
    }

    public Set<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(Set<JobApplication> applications) {
        this.applications = applications;
    }

//    public Group getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(Group ownerGroup) {
//        this.ownerGroup = ownerGroup;
//    }
}
