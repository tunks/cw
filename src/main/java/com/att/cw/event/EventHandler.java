/**
 *
 *  AT&T Service Assurance Team copyright 2016
 *
 */
package com.att.cw.event;

/**
 * * EventHandler- receives incoming messages
 *
 * @author ebrimatunkara
 * @param <E>
 */
public interface EventHandler<E> {

    /**
     * Receive incoming event message
     *
     * @param event
     */
    public void handle(E event);
}
