/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;

import com.att.cw.model.Message;
import com.att.cw.model.MessageHeader;
import com.att.cw.model.Participant;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * CWMessageBuilder - implementation of IMessageBuilder interface This
 * class prepares Report mail message subject, content,send address, recipient
 * addresses
 *
 * @author ebrimatunkara
 */
@Component("mailMessageBuilder")
public class MailMessageBuilder implements IMessageBuilder<Message, SimpleMailMessage> {
    @Override
    public SimpleMailMessage createMessage(Message object) {
        return createMailMessage(object);
    }

    //Embed and create mime email message
    private SimpleMailMessage createMailMessage(Message object) {
        MessageHeader header = object.getHeader();
            String[] recipients = header.getRecipients()
                .stream()
                .map(new RecipientMapper())
                .collect(Collectors.toList())
                .toArray(new String[header.getRecipients().size()]);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(header.getSender());
        message.setTo(recipients);
        message.setSubject(object.getSubject());
        message.setText(object.getContent());
        return message;
    }
     /**
     * Participant mapper to String 
     **/
    private class RecipientMapper implements Function<Participant, String> {

        @Override
        public String apply(Participant t) {
            return t.getEmail();
        }
    }
}
