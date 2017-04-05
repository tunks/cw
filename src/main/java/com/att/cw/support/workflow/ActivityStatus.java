/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.workflow;

import com.att.cw.model.JobActivity;

/**
 * State events
 *
 * @author ebrimatunkara
 */
public enum ActivityStatus implements StateOperation<JobActivity> {
    SEND_MAIL(new SendMailOperation()),
    /**
     * Job application automatic review status
     */
    AUTO_REVIEW(new AutoReviewOperation()),
    /**
     * Job application manual review status
     */
    REVIEW(new ReviewOperation()),
    /**
     * Job application interview status
     */
    INTERVIEW(new InterviewOperation()),
    /**
     * Job application reject status
     */
    REJECT(new RejectOperation());

    private final StateOperation operation;

    ActivityStatus(StateOperation operation) {
        this.operation = operation;
    }

    @Override
    public void action(JobActivity object) {
        operation.action(object);
    }
}
