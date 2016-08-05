/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.model;

import org.springframework.data.annotation.Id;

/**
 *
 * @author ebrimatunkara
 */

public class Mail {
    @Id
    private String id;
    private Message message;
    
    public String getRecipient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSubject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
