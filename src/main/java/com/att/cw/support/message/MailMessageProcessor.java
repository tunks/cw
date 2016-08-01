/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;


import com.att.cw.model.Message;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Kafka Message consumer -- asynchronously receives messages 
 * @author ebrimatunkara
 */
@Component("mailMessageProcessor")
public class MailMessageProcessor implements MessageProcessor<Message>{
    /**
     * EmailNotification service
     */
    @Autowired
    private Notification mailNotificaton;
    /**
     * Mail message builder 
     **/
    @Autowired
    private MailMessageBuilder  mailMessageBuilder;
    
    private final ExecutorService executor = Executors.newFixedThreadPool(50);
    
    @Override
    public  void process(Message payload){
          mailNotificaton.send(payload);
    }

}
