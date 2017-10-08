/**
 *
 *  AT&T Service Assurance Team copyright 2016
 */
package com.att.cw.event;

import com.att.cw.dto.mappers.BaseEntityMapper;

/**
 * SolrEvent class
 *
 * @author Ebrima
 */
public class SolrEvent {

    private EventAction action;
    private String[] excludedFields;
    private Object data;
    private BaseEntityMapper entityMapper;

    public SolrEvent() {
    }

    public EventAction getAction() {
        return action;
    }

    public void setAction(EventAction action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String[] getExcludedFields() {
        return excludedFields;
    }

    public void setExcludedFields(String[] excludedFields) {
        this.excludedFields = excludedFields;
    }

    public BaseEntityMapper getEntityMapper() {
        return entityMapper;
    }

    public void setEntityMapper(BaseEntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @Override
    public String toString() {
        return "Event{" + "action=" + action + ", data=" + data + '}';
    }

    /**
     * EventBuilder - creates new instance of event object
     */
    public static class EventBuilder {

        private final SolrEvent event;

        public EventBuilder() {
            event = new SolrEvent();
        }

        public EventBuilder setAction(EventAction action) {
            this.event.setAction(action);
            return this;
        }

        public EventBuilder setData(Object data) {
            this.event.setData(data);
            return this;
        }

        public EventBuilder setExcludeFields(String[] fields) {
            this.event.setExcludedFields(fields);
            return this;
        }
        
        public EventBuilder setEntityMapper(BaseEntityMapper entityMapper) {
            this.event.setEntityMapper(entityMapper);
            return this;
        }

        public SolrEvent build() {
            return event;
        }
    }
}
