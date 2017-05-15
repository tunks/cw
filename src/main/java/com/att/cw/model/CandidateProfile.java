package com.att.cw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * UserProfile entity model
 *
 * @author dileep
 */
@Entity
@Table(name = "CANDIDATE_PROFILE")
@PrimaryKeyJoinColumn(name="id")
public class CandidateProfile extends UserProfile{
	
	
	@Column(name = "first_name", length = 100)
    @NotNull(message = "First Name cannot be null")
    @Size(min = 1, max = 100)
    private String firstName;

	@Column(name = "last_name", length = 100)
    //@NotNull(message = "Last Name cannot be null")
    //@Size(min = 1, max = 100)
    private String lastName;
	
	@Column(name = "date_of_birth")
    @NotNull(message = "dateOfBirth cannot be null")
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateOfBirth;
	
	 @Enumerated(EnumType.STRING)
	 @Column(name = "gender")
	 @NotNull(message = "gender cannot be null")
	 private Gender gender;
	
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
