/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.event;

import com.att.cw.model.Participant;
import java.util.Set;

/**
 *
 * @author ebrimatunkara
 */
public class MessageEvent {
    private Set<Participant> recipients;
    private String sender;
    private String subject;
    private String content;
    
    public Set<Participant> getRecipients() {
        return recipients;
    }
    
    public void setRecipients(Set<Participant> recipients) {
        this.recipients = recipients;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "MessageEvent{" + "recipients=" + recipients + ", sender=" + sender + ", subject=" + subject + ", content=" + content + '}';
    }
    
    public static class MessageEventBuilder {

        private final MessageEvent event;
        
        public MessageEventBuilder() {
            event = new MessageEvent();
        }
        
        public MessageEventBuilder setSubject(String subject) {
            event.setSubject(subject);
            return this;
        }
        
        public MessageEventBuilder setRecipients(Set<Participant> recipients) {
            event.setRecipients(recipients);
            return this;
        }
        
        public MessageEventBuilder setContent(String content) {
            event.setContent(content);
            return this;
        }
        
        public MessageEventBuilder setSender(String sender) {
            this.event.setSender(sender);
            return this;
        }
        
        public MessageEvent build() {
            return event;
        }      
    }
}
