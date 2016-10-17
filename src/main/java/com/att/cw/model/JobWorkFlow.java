package com.att.cw.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobWorkFlow entity class
 * Open job  
 *     -> submissions/close job
 *          -> review (automatic select/manual selection)
 *                 -> rejection (send message to candidates automatically about reject) 
 *                 -> interview(selected candidates with employees)
 *                    -> phone
 *                    -> onsite
 *                        -> review
 *                    -> 
 *
 */
@Entity
@Table(name = "JOB_WORKFLOW")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class JobWorkFlow extends Audit<Long> {
     private static final long serialVersionUID = 1L;
    /**
     * work flow id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * work flow activities
     */
   
    @OneToMany // with a join table
//    @JoinTable(
//        uniqueConstraints=@UniqueConstraint(columnNames={"Work_ID","users_ID"})
//    )
    private Set<JobActivity> activities;

     @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<JobActivity> getActivities() {
        return activities;
    }

    public void setActivities(Set<JobActivity> activities) {
        this.activities = activities;
    }

}
