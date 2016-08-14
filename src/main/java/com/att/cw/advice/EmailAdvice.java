/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.advice;

import com.att.cw.model.Message;
import com.att.cw.model.MessageHeader;
import com.att.cw.model.Participant;
import com.att.cw.model.User;
import com.att.cw.support.message.MailMessageBuilder;
import com.att.cw.support.message.MessageProcessor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * EmailAdvice- Aspect Oriented class that deligates email messages on pointcut
 * methods
 *
 * @author ebrimatunkara
 */
@Aspect
public class EmailAdvice {
    /**
     * Message processor
     */
    @Autowired
    private MessageProcessor mailMessageProcessor;
    /**
     * Mail message builder 
     **/
    @Autowired
    private MailMessageBuilder  mailMessageBuilder;
    
    @Autowired
    private String activateMailContent;
    
    @Value("${mail.noreply}")
    private String noreply;

    /**
     * Send email when new user is created and registered
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(
            pointcut = "execution(* com.att.cw.service.UserService.save(..))",
            returning = "retVal")
    public void userRegistered(JoinPoint joinPoint, Object retVal) {
            System.out.println("Sending email here !!");
            User  user = (User)retVal;
            Set<Participant> recipients = new HashSet();
            recipients.add(new Participant(user.getEmail()));
            MessageHeader header = new MessageHeader();
            header.setSender(noreply);
            header.setRecipients(recipients);
            Message message = new Message();
            message.setHeader(header);
            message.setSubject("Activate user registration");
            message.setContent(activateMailContent);
            SimpleMailMessage mailMessage = mailMessageBuilder.createMessage(message);
            mailMessageProcessor.process(mailMessage);
            //save in the message
    }
}
