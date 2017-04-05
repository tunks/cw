package com.att.cw.model;

import com.att.cw.listener.CandidateEntityListener;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    private User user;
//    /**
//     * Job candidate application
//         *
//     */
//    @OneToOne(mappedBy = "candidate")
//    private JobApplication application;
    @Column(name = "candiate_number", columnDefinition = "BINARY(16)")
    private UUID candidateNumber;

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

//    public JobApplication getApplication() {
//        return application;
//    }
//
//    public void setApplication(JobApplication application) {
//        this.application = application;
//    }
    public UUID getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(UUID candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

}
