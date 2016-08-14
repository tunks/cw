/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import javax.persistence.Embeddable;

/**
 * Job entity status
 * @author ebrimatunkara
 */
@Embeddable
public enum JobStatus {
    OPEN,CLOSE;
}
