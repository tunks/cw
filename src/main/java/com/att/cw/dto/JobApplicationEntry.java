/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

/**
 *
 * @author ebrimatunkara
 */
public class JobApplicationEntry {

    private Long id;
    private Long jobId;
    private Long userId;
    private String title;
    private boolean submitted = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isSubmitted() {
        return submitted;
    }
    
    @Override
    public String toString() {
        return "{" + "id=" + id + ", jobId=" + jobId + ", userId=" + userId + '}';
    }

}
