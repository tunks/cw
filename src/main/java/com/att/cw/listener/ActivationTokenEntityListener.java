/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.model.ActivationToken;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.PrePersist;

/**
 * ActivationTokenEntity listener
 * @author ebrimatunkara
 */
public class ActivationTokenEntityListener {
    private static final int EXPIRATION = 60 * 24;

    @PrePersist
    void onCreate(ActivationToken entity) {
        entity.setVerified(false);
        entity.setExpiryDate(calculateExpiryDate(EXPIRATION));
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
