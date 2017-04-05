/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.workflow;

import com.att.cw.model.JobActivity;

/**
 *
 * @author ebrimatunkara
 */
public class RejectOperation implements StateOperation<JobActivity> {

    @Override
    public void action(JobActivity object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
