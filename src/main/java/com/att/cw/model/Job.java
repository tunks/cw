package com.att.cw.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

   // @OneToMany(mappedBy = "job")
    //private Set<JobVacancy> jobVacancies;
    /**
     * department or group that owns the job
     */
    
    //@ManyToOne
    //private Group ownerGroup;

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

//    public Set<JobVacancy> getJobVacancies() {
//        return jobVacancies;
//    }
//
//    public void setJobVacancies(Set<JobVacancy> jobVacancies) {
//        this.jobVacancies = jobVacancies;
//    }

//    public Group getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(Group ownerGroup) {
//        this.ownerGroup = ownerGroup;
//    }
}
