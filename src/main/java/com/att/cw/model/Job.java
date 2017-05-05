package com.att.cw.model;

import com.att.cw.listener.JobEntityListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Job entity class
 */
@Entity
@EntityListeners(JobEntityListener.class)
@Table(name = "JOB")
public class Job extends Audit<Long> {

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
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 16777215)
    private byte[] description;
    /**
     * job responsibilities
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 16777215)
    private byte[] responsibilities;

    /**
     * job skills
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 16777215)
    private byte[] skills;

    /**
     * Job category
     */
    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "JOB_CATEGORY_ITEMS",
            joinColumns = {
                @JoinColumn(name = "job_id", nullable = true, referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "category_id", nullable = true, referencedColumnName = "id")})
    private Set<JobCategory> categories = new HashSet();
    /**
     * employment type [FULLTIME, PART_TIME,CONTRACT, INTERNSHIP]
     */
    @ManyToOne
    private JobType jobType;
    /**
     * job work flow
     */
//    @OneToOne
//    private JobWorkFlow workflow;
    /**
     * Job vacancy
     */
    @Embedded
    private JobVacancy vacancy;
    /**
     * department or group that owns the job
     */
//    @OneToOne
//    private Group ownerGroup;
    /**
     * Job location
     */
    @Embedded
    private JobLocation location;
    /**
     * Job questions
     */
    @OneToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<JobQuestion> questions = new HashSet();

    public Job() {
    }

    public Job(String title, byte[] description) {
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

    public byte[] getDescription() {
        return description;
    }

    public void setDescription(byte[] description) {
        this.description = description;
    }

    public byte[] getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(byte[] responsibilities) {
        this.responsibilities = responsibilities;
    }

    public JobVacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(JobVacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Set<JobQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<JobQuestion> questions) {
        this.questions = questions;
    }

    public void addQuestion(JobQuestion question) {
        this.questions.add(question);
    }

    public Set<JobCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<JobCategory> categories) {
        this.categories = categories;
    }

    public JobLocation getLocation() {
        return location;
    }

    public void setLocation(JobLocation location) {
        this.location = location;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public byte[] getSkills() {
        return skills;
    }

    public void setSkills(byte[] skills) {
        this.skills = skills;
    }

//    public JobWorkFlow getWorkflow() {
//        return workflow;
//    }
//
//    public void setWorkflow(JobWorkFlow workflow) {
//        this.workflow = workflow;
//    }
//    public Group getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(Group ownerGroup) {
//        this.ownerGroup = ownerGroup;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Job other = (Job) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", title=" + title
                + " categories=" + categories + ", jobType="
                + jobType + ", vacancy=" + vacancy + ", location=" + location + "}";
    }

}
