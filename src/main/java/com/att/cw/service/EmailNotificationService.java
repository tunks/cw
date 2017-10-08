/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.Message;
import com.att.cw.model.MessageHeader;
import com.att.cw.model.Participant;
import com.att.cw.support.message.MailMessageBuilder;
import com.att.cw.support.message.MessageProcessor;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("emailNotificationService")
public class EmailNotificationService implements NotificationService<Set<Participant>, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationService.class);

    @Autowired
    private MessageProcessor mailMessageProcessor;
    /**
     * Mail message builder
     *
     */
    @Autowired
    private MailMessageBuilder mailMessageBuilder;

    @Value("${mail.noreply}")
    private String noreply;

    @Override
    public void send(Set<Participant> recipients, String subject, String content) {
        send(noreply, recipients, subject, content);
    }

    @Async
    @Override
    public void send(String sender, Set<Participant> recipients, String subject, String content) {
        try {
            MessageHeader header = new MessageHeader();
            header.setSender((sender != null)? sender : noreply);
            header.setRecipients(recipients);
            Message message = new Message();
            message.setHeader(header);
            message.setSubject(subject);
            message.setContent(content);
            SimpleMailMessage mailMessage = mailMessageBuilder.createMessage(message);
            mailMessageProcessor.process(mailMessage);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
