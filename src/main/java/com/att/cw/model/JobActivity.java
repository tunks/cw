package com.att.cw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Job Activity
 */
@Entity
@Table(name = "JOB_ACTIVITYZ")
public class JobActivity extends Audit<Long> {
     private static final long serialVersionUID = 1L;
    /**
     * job activity id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Activity name
     */
    @NotNull
    private String name;
    /**
     *  Activity description
     */
    private String description;
    /**
     * Activity parent work flow
     */
    @OneToOne
    private JobWorkFlow workflow;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobWorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(JobWorkFlow workflow) {
        this.workflow = workflow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
