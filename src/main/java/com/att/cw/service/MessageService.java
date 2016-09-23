/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.service;


import com.att.cw.model.Mail;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.att.cw.dao.MessageRepository;
import com.att.cw.model.Message;

/**
 * Mail CRUD service implementation
 * @author ebrimatunkara
 */
@Service("messageService")
public class MessageService implements CrudService<Message,Long> {
    /**
     * Message repository
     **/
    @Resource
    private MessageRepository messageRepository;

    @Override
    public Message save(Message object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Message> findAll(Pageable page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
