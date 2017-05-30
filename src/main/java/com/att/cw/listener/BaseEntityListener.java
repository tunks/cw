/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.dto.mappers.BaseEntityMapper;
import com.att.cw.event.SolrEvent;
import com.att.cw.event.SolrEvent.EventBuilder;
import com.att.cw.event.EventAction;
import com.att.cw.event.MessageEvent;
import com.att.cw.model.Job;
import com.att.cw.model.Participant;
import java.util.Set;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author ebrimatunkara
 */
public abstract class BaseEntityListener {

    private static final Logger logger = LoggerFactory.getLogger(BaseEntityListener.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    @PrePersist
    void onCreate(Job entity) {
        logger.info("on create ");
    }

    @PreUpdate
    void onPersist(Job entity) {
        logger.info("pre persist ");
    }

    public void postPersist(Object target) {
        logger.info("post persist ");
        indexObjectToSearchableContent(target, EventAction.ENTITY_CREATED);
    }

    public void postUpdate(Object target) {
        logger.info("post update ");
        indexObjectToSearchableContent(target, EventAction.ENTITY_UPDATED);
    }

    @PostRemove
    public void postDelete(Object target) {
        //publisher.publishEvent(new OnDeletedEvent<>(this, target));
    }

    private void indexObjectToSearchableContent(Object target, EventAction action) {
        try {
            setBeanAutowiringSupport();
            SolrEvent event = new EventBuilder()
                    .setAction(action)
                    .setData(target)
                    //.setExcludeFields(getExcludedFields())
                    .setEntityMapper(getEntityMapper())
                    .build();
            publisher.publishEvent(event);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    protected void setBeanAutowiringSupport() {
        if (publisher == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        }
    }

    protected ApplicationEventPublisher getEventPublisher() {
        return publisher;
    }

    protected void publishEventMessage(String subject, Set<Participant> recipients, String content) {
        if (getEventPublisher() == null) {
            this.setBeanAutowiringSupport();
        }
        
        if (publisher != null) {
            MessageEvent event = new MessageEvent.MessageEventBuilder()
                    .setSubject(subject)
                    .setRecipients(recipients)
                    .setContent(content)
                    .build();
            publisher.publishEvent(event);
        }
    }

    public abstract BaseEntityMapper getEntityMapper();

    protected abstract String[] getExcludedFields();
}
