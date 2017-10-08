/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.event;

import com.att.cw.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

/**
 *
 * @author ebrimatunkara
 */
public class MailMessageHandler  implements EventHandler<MessageEvent>{
    @Autowired
    private EmailNotificationService emailNotificationService;
  
    @EventListener
    @Override
    public void handle(MessageEvent event) {
         emailNotificationService.send(event.getSender(), event.getRecipients(), event.getSubject(),event.getContent());
         System.out.println("mail message handler event  "+event);
    }    
}
