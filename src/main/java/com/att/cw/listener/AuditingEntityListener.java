/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.Audit;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/**
 * Audit Listener 
 * @author ebrimatunkara
 */
public class AuditingEntityListener {
	@PrePersist
	void onCreate(Object entity) {
           if(entity instanceof Audit){
             Date date = new Date();
             Audit audit = (Audit) entity;
             audit.setCreatedDate(date);
             audit.setLastModifiedDate(date);
           }
	}
	
	@PreUpdate
	void onPersist(Object entity) {
            if(entity instanceof Audit){
               Date date = new Date();
               Audit audit = (Audit) entity;
               audit.setLastModifiedDate(date);
               audit.setLastModifiedDate(date);
            }	
	}
}
