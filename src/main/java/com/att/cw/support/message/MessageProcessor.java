/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;

/**
 * Message Processor interface
 *
 * @author ebrimatunkara
 */
public interface MessageProcessor<T> {

    public void process(T payload);
}
