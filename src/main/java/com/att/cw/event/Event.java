/**
 *
 *  AT&T Service Assurance Team copyright 2016
 */
package com.att.cw.event;

/**
 * Event class
 *
 * @author Ebrima
 */
public class Event {

    private EventAction action;
    private String[] excludedFields;
    private Object data;

    public Event() {
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

    @Override
    public String toString() {
        return "Event{" + "action=" + action + ", data=" + data + '}';
    }

    /**
     * EventBuilder - creates new instance of event object
     */
    public static class EventBuilder {

        private final Event event;

        public EventBuilder() {
            event = new Event();
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

        public Event build() {
            return event;
        }
    }
}
