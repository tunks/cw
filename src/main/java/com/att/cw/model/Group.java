/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "GROUP")
public class Group extends Audit<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //@OneToOne
    //  @Embedded
    //private GroupProfile profile;
    //@OneToMany(mappedBy = "ownerGroup")
    //private Set<Job> groupJobs;
//    
//    @OneToMany
//    private Set<GroupMember> members;
    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public GroupProfile getProfile() {
//        return profile;
//    }
//
//    public void setProfile(GroupProfile profile) {
//        this.profile = profile;
//    }
//    public Set<Job> getGroupJobs() {
//        return groupJobs;
//    }
//
//    public void setGroupJobs(Set<Job> groupJobs) {
//        this.groupJobs = groupJobs;
//    }   
//    public Set<GroupMember> getMembers() {
//        return members;
//    }
//
//    public void setMembers(Set<GroupMember> members) {
//        this.members = members;
//    }
}
