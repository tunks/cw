/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import com.att.cw.listener.PasswordChangeListener;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author ebrimatunkara
 */
@MappedSuperclass
@EntityListeners(PasswordChangeListener.class)
public abstract class UserAudit extends Audit{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastPasswordResetDate;
    
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
