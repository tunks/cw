/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;

/**
 * Notification service interface
 *
 * @author ebrimatunkara
 * @param <T>
 */
public interface Notification<T> {

    public void send(T message);
}
