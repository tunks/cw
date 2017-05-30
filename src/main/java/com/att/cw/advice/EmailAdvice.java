/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.advice;

import com.att.cw.event.MessageEvent;
import com.att.cw.event.MessageEvent.MessageEventBuilder;
import com.att.cw.event.MessagePublisher;
import com.att.cw.model.Participant;
import com.att.cw.model.User;
import com.att.cw.security.JwtUtil;
import com.att.cw.service.EmailNotificationService;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
    private static final Logger logger = LoggerFactory.getLogger(EmailAdvice.class);
    /**
     * Token Generation utility
     */
    @Autowired
    JwtUtil jwtUtil;
    
    @Autowired
    private String activateMailContent;
    
    @Autowired
    private MessagePublisher  messagePublisher;
    
    @Value("${reg.server.name}")
    private String serverName;
    
    @Value("${reg.server.port}")
    private String serverPort;
    
    @Autowired
    private ServletContext context;

    /**
     * Send email when new user is created and registered
     *
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(
            pointcut = "execution(* com.att.cw.service.RegistrationService.registerUser(..))",
            returning = "retVal")
    public void userRegistered(JoinPoint joinPoint, Object retVal) {
        try {
            User user = (User) retVal;
            logger.info("Sending Email to user...:" + user.getEmail());
            String url = "http://" + serverName + ":" + serverPort + context.getContextPath() + "/open/register/confirm/";
            activateMailContent = activateMailContent.replace("${title}", "Confirm your registration");
            activateMailContent = activateMailContent.replace("${message}", "Click on the below URL or copy paste it to a browser in order to activate your account");
            activateMailContent = activateMailContent.replace("${link}", url + jwtUtil.generateRegistrationToken(user));
            String subject = "Activate cw user registration";
            logger.info(activateMailContent);
            Set<Participant> recipients = new HashSet();
            recipients.add(new Participant(user.getEmail()));
            MessageEvent event = new MessageEventBuilder()
                                        .setSubject(subject)
                                        .setRecipients(recipients)
                                        .setContent(activateMailContent)
                                        .build();
            messagePublisher.publish(event);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

}
