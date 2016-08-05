package com.att.cw.support.message;

/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */

/**
 * Mail Message interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface IMessageBuilder<T,V> {
      public V createMessage(T object) ;
}
