/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * UserProfile entity model
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "user_profile")
public class UserProfile extends Profile {
    @Column(name="first_name")
    @NotNull
    private String firstName;
    
    @Column(name="middle_name")
    private String middleName;
    
    @Column(name="last_name")
    @NotNull
    private String lastName;
    
    @Column(name="date_of_birth")
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name="phone_number")
    private String phoneNumber;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
