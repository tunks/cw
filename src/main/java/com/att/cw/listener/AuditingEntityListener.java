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
             Audit audit = (Audit) entity;
             audit.setCreatedDate(new Date());
             //audit.setCreatedDate(new DateTime());
             System.out.println("-------- on create ------");
           }
	}
	
	@PreUpdate
	void onPersist(Object entity) {
            if(entity instanceof Audit){
               Audit audit = (Audit) entity;
               audit.setLastModifiedDate(new Date());
              //audit.setLastModifiedDate(new DateTime());
               System.out.println("-------- last modified date ------");
            }	
	}
}
