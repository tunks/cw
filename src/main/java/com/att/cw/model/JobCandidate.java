package com.att.cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JobCandidate entity class
 **/
@Entity
@Table(name = "job_candidates")
public class JobCandidate extends Audit<Long>{
        private static final long serialVersionUID = 1L;
	/**
	 * candidate id
	 **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
        /**
         * Job candidate user id
         **/
	private String userId;
        /**
         * Job candidate application
         **/
        @OneToOne(mappedBy = "candidate")
	private JobApplication application;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}
}
