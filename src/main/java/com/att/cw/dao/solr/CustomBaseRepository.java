/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.dao.solr;

import com.att.cw.support.SearchOperation;
import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * CustomBaseRepository interface
 *
 * @author ebrimatunkara
 * @param <T>, Repository model class type
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomBaseRepository<T, ID extends Serializable> extends SearchOperation<T> {

}
