/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 *
 * @author ebrimatunkara
 */
public class MessagePublisher implements BasePublisher<MessageEvent>{
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Override
    public void publish(MessageEvent event) {
         eventPublisher.publishEvent(event);
    }
}
