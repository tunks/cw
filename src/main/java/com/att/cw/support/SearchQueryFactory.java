/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.support;

import org.springframework.data.domain.Pageable;

/**
 * SearchQueryFactory interface
 *
 * @author ebrimatunkara
 *
 */
public interface SearchQueryFactory<T, V> {

    public T createQuery(V params);

    public T createQuery(V params, Pageable page);
}
