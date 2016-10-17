/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.workflow;

import com.att.cw.model.JobWorkFlow;
import com.att.cw.model.StandardJobWorkFlow;

/**
 *
 * @author ebrimatunkara
 */
public class WorkFlowFactory {
      public JobWorkFlow createDefaultJobWorkFlow(){   
         return new StandardJobWorkFlow();
      }
}
