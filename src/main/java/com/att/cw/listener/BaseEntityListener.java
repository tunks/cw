/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.listener;

import com.att.cw.event.Event;
import com.att.cw.event.Event.EventBuilder;
import com.att.cw.event.EventAction;
import com.att.cw.model.Job;
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
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            Event event = new EventBuilder()
                    .setAction(action)
                    .setData(target)
                    .setExcludeFields(getExcludedFields())
                    .build();
            publisher.publishEvent(event);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    protected abstract String[] getExcludedFields();
}
