/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.dao.solr;

import com.att.cw.model.SearchableDocument;
import com.att.cw.support.SearchQueryFactory;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.repository.query.SolrEntityInformation;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.util.MultiValueMap;

/**
 * Solr Custom Repository concrete implementation Document Repository
 *
 * @author ebrimatunkara
 * @param <T>
 */
//@Repository
public class SearchableDocumentRepositoryImpl
        extends SimpleSolrRepository<SearchableDocument, Long>
        implements CustomSolrRepository<SearchableDocument, Long> {

    private SearchQueryFactory<Query, MultiValueMap> queryFactory;

    public SearchableDocumentRepositoryImpl(SearchQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public SearchableDocumentRepositoryImpl(SearchQueryFactory queryFactory, SolrOperations solrOperations) {
        super(solrOperations);
        this.queryFactory = queryFactory;
    }

    public SearchableDocumentRepositoryImpl(SearchQueryFactory queryFactory, SolrEntityInformation<SearchableDocument, Long> metadata, SolrOperations solrOperations) {
        super(metadata, solrOperations);
        this.queryFactory = queryFactory;
    }

    public SearchableDocumentRepositoryImpl(SearchQueryFactory queryFactory, SolrOperations solrOperations, Class<SearchableDocument> entityClass) {
        super(solrOperations, entityClass);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<SearchableDocument> search(MultiValueMap params, Pageable page) {
        Query query = queryFactory.createQuery(params, page);
        return getSolrOperations().query(query, getEntityClass());
    }

    @Override
    public Page<SearchableDocument> search(MultiValueMap params) {
        Query query = queryFactory.createQuery(params);
        return getSolrOperations().query(query, getEntityClass());
    }
}
