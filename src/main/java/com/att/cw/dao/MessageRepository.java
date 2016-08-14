/*
 * Simple mail service to send email messages to recipients
 * 2016 © ATT Service Assurance  - Raptor POC team 
 */
package com.att.cw.dao;

import com.att.cw.model.Message;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Message Repository interface
 * @author ebrimatunkara
 */
public interface MessageRepository extends PagingAndSortingRepository<Message,Long>{
    
}
