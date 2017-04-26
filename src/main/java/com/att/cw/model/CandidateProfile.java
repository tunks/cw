package com.att.cw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * UserProfile entity model
 *
 * @author dileep
 */
@Entity
@Table(name = "CANDIDATE_PROFILE")
public class CandidateProfile extends UserProfile{
	
	@Column(name = "first_name")
    @NotNull
    private String firstName;

    
    @Column(name = "last_name")
    @NotNull
    private String lastName;

}
