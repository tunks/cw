package com.att.cw.model;

import com.att.cw.support.workflow.ActivityStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
/**
 * Job Activity
 */
@Entity
@Table(name = "JOB_ACTIVITY",uniqueConstraints={
   @UniqueConstraint(columnNames={"workflow_id", "status"}),
})
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
    /**
     * previous activity
     */ 
    @OneToOne
    @JoinColumn(name="previous_activity_id",referencedColumnName="id")
    private JobActivity previousActivity;
    
    /**
     * Job activity status
     */
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    public JobActivity() {
    }

    public JobActivity(ActivityStatus status) {
        this.status = status;
    }
    
    public JobActivity(JobWorkFlow workflow, ActivityStatus status) {
        this.workflow = workflow;
        this.status = status;
    }
    
    public JobActivity(String name, ActivityStatus status) {
        this.name = name;
        this.status = status;
    }

    public JobActivity(String name, JobWorkFlow workflow, ActivityStatus status) {
        this.name = name;
        this.workflow = workflow;
        this.status = status;
    }
    

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

    public JobActivity getPreviousActivity() {
        return previousActivity;
    }

    public void setPreviousActivity(JobActivity previousActivity) {
        this.previousActivity = previousActivity;
    }

    
    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        if(status != null && this.status != status){
           this.status = status;
         }
    }
    
    
}
