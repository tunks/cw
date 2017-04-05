/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * MailNotification Service --- Sends mail messages through STMP protocols
 *
 * @author ebrimatunkara
 */
@Service("mailNotificaton")
public class MailNotification implements Notification<SimpleMailMessage> {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send mail messages
     *
     * @param message
     *
     */
    @Override
    public void send(SimpleMailMessage message) {
        try {
            mailSender.send(createMimeMessage(message));
        } catch (MessagingException ex) {
            Logger.getLogger(MailNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* create mime message */
    private MimeMessage createMimeMessage(SimpleMailMessage message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(message.getFrom());
        helper.setTo(message.getTo());
        helper.setSubject(message.getSubject());
        helper.setText(message.getText(), true);
        return mimeMessage;
    }
}
