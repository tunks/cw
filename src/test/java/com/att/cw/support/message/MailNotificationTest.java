///*
// * Simple mail service to send email messages to recipients
// * 2016 © ATT Service Assurance  - Raptor POC team 
// */
//package com.att.cw.support.message;
//
//import static com.att.cw.support.message.WiserAssertions.assertReceivedMessage;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.subethamail.wiser.Wiser;
//
///**
// * EmailNotificationService test
// *
// * @author ebrimatunkara
// */
//@ActiveProfiles({"test", "dev"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
//@WebAppConfiguration
//public class MailNotificationTest {
//
//    @Autowired
//    private Notification mailNotificaton;
//
//    @Value("${mail.port}")
//    String mailport;
//
//    private Wiser wiser;
//    private SimpleMailMessage message;
//    private final String mailFrom = "some1@localhost";
//    private final String mailTo = "some2@localhost";
//    private final String subject = "Hello world";
//    private final String text = "Hello world, testing send mail!";
//
//    public MailNotificationTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        //mock wiser mail test server
//        wiser = new Wiser();
//        wiser.setPort(Integer.parseInt(mailport));
//        wiser.start();
//        //create the mail message
//        message = new SimpleMailMessage();
//        message.setTo(mailTo);
//        message.setFrom(mailFrom);
//        message.setSubject(subject);
//        message.setText(text);
//        //send mail message
//        mailNotificaton.send(message);
//    }
//
//    @After
//    public void tearDown() {
//        wiser.stop();
//    }
//
//    /**
//     * Test of send method, of class EmailNotificationService.
//     */
//    @Test
//    public void testSend() {
//        assertReceivedMessage(wiser)
//                .from(mailFrom)
//                .to(mailTo)
//                .withSubject(subject);
//    }
//
//}
