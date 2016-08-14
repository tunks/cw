package com.att.cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobCandidate entity class
 **/
@Entity
@Table(name = "JOB_CANDIDATE")
public class JobCandidate extends Audit<Long>{
        private static final long serialVersionUID = 1L;
	/**
	 * candidate id
	 **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
        /**
         * Job candidate mapped to user
         **/
     
        @ManyToOne
	private User user;
        /**
         * Job candidate application
         **/
        @OneToOne(mappedBy = "candidate")
	private JobApplication application;

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

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}
}
