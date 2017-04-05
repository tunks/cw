/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "MESSAGE_HEADER")
public class MessageHeader extends Audit<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<Participant> recipients;
    private String sender;

    @Override
    public Long getId() {
        return id;
    }

    public Set<Participant> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Participant> recipients) {
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
