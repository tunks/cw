package com.att.cw.model;

import com.att.cw.listener.CandidateEntityListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobCandidate entity class
 *
 */
@Entity
@EntityListeners(CandidateEntityListener.class)
@Table(name = "JOB_CANDIDATE")
public class JobCandidate extends Audit<Long> {

    private static final long serialVersionUID = 1L;
    /**
     * candidate id
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Job candidate mapped to user
     *
     */
    @OneToOne(cascade={MERGE,REMOVE})
    private User user;

    @Column(name = "candiate_number")
    private Long candidateNumber;
    
    @OneToMany( cascade={MERGE}, fetch = FetchType.LAZY, mappedBy="candidate", orphanRemoval = true)
    private List<JobApplication> applications = new  ArrayList();

    public JobCandidate() {
    }

    public JobCandidate(User user) {
        this.user = user;
    }
   
    /**
     * object id
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(Long candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public List<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplication> applications) {
        this.applications = applications;
    }

    
    @Override
    public String toString() {
        return "JobCandidate{" + "id=" + id + ", user=" + user + ", candidateNumber=" + candidateNumber + ", applications=" + applications + '}';
    }
}
