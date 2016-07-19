/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * UserProfile entity model
 * @author ebrimatunkara
 */
@Entity
@Table(name="user_profile")
public class UserProfile extends Profile{
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    
    @OneToOne
    private User user;
    @OneToOne
    private Address address;
}
