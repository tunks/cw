/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.support.message;

import com.att.cw.model.Mail;
import com.att.cw.model.Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${mail.noreply}")
    private String noreply;

    @Autowired
    private String activateMailContent;

    @Override
    public SimpleMailMessage createMessage(Message object) {
        return createMailMessage(object);
    }

    //Embed and create mime email message
    private SimpleMailMessage createMailMessage(Message object) {
//        String subject = mailSubject.concat("-"+object.getSubject());
//        //embed report message into html message content
//        String content = new  HtmlEmbeddedContent(object)
//                             .embedContent( mailHtmlContent);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mailFrom);
//        message.setTo(object.getRecipient());
//        message.setSubject(subject);
//        message.setText(content);
//        return message;
      return null;
    }
    /**
     * Embed content interface
     **/
    private interface EmbeddedContent {

        public String embedContent(String content);

        public String embedContent(String content, String pattern) throws FileNotFoundException;
    }

    /**
     * HTML embedded content
     *
     */
    private class HtmlEmbeddedContent implements EmbeddedContent {
        private final Mail object;

        public HtmlEmbeddedContent(Mail object) {
            this.object = object;
        }

        /**
         * Embed contents
         **/
        @Override
        public String embedContent(String content) {
            return embedContentFormats(content);
        }

        @Override
        public String embedContent(String content, String pattern) throws FileNotFoundException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String embedContentFormats(String content){
//            //embed report title nd date
//            String _content = content.replaceAll("\\$\\{title\\}",  object.getName().toString())
//                                     .replaceAll("\\$\\{createdOn\\}",object.getCreatedOn().toString());
            //embed report links
            return embedLinks(content);
        }

        private String embedLinks(String content) {
//            for (MessageFile file : object.getFiles()) {
//                String link = file.getUrl().toString();
//                String format = file.getFormat().toString().toLowerCase();
//                content = content.replaceAll("\\$\\{link:" + format + "\\}", link);
//            }
            return content;
        }
    }

    /**
     * File Embedded content
     *
     */
    private class FileEmbeddedContent implements EmbeddedContent {
        private final String embedHTML = "${embeded:html}";
        private final File file;

        public FileEmbeddedContent(String filePath) {
            this.file = new File(filePath);
        }

        @Override
        public String embedContent(String content) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String embedContent(String content, String pattern) throws FileNotFoundException {
            if (content.contains(embedHTML)) {
                try {
                    StringBuilder sbuilder = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sbuilder.append(line);
                        }
                    }
                    return content.replaceFirst(pattern, sbuilder.toString());
                } catch (IOException ex) {
                    Logger.getLogger(MailMessageBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return content;
        }
    }
}
