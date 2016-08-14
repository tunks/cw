/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.UserAudit;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/**
 * Audit Listener 
 * @author ebrimatunkara
 */
public class PasswordChangeListener {
	@PrePersist
	void onCreate(UserAudit user) {
             Date date = new Date();
             user.setLastPasswordResetDate(date);
	}
	
	@PreUpdate
	void onPersist(Object entity) {
            if(entity instanceof UserAudit){
              //entity.setLastPasswordResetDate(date);
            }	
	}
}
