/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.advice;

import com.att.cw.support.message.MessageProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * EmailAdvice- Aspect Oriented class that deligates email messages on pointcut
 * methods
 *
 * @author ebrimatunkara
 */
@Aspect
public class EmailAdvice {
    @Autowired
    private MessageProcessor mailMessageProcessor;
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
            //mailMessageProcessor.process(retVal);
    }
}
