/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.Audit;
import com.att.cw.model.JobCandidate;
import java.util.Date;
import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author ebrimatunkara
 */
public class CandidateEntityListener {
    @PrePersist
	void onCreate(JobCandidate entity) {
           entity.setCandidateNumber(UUID.randomUUID());
	}
}
