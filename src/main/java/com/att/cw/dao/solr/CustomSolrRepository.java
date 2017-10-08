/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao.solr;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomSolrRepository<T, ID extends Serializable> extends SolrCrudRepository<T, ID>, CustomBaseRepository<T, ID> {

}
