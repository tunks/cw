package com.att.cw.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobWorkFlow entity class
 *
 */
@Entity
@Table(name = "job_workflows")
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
    @OneToMany
    private Set<JobActivity> activities;

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
