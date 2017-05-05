/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.support.solr;

import com.att.cw.model.Searchable;
import com.att.cw.support.DataUtils;
import com.att.cw.support.SearchQueryFactory;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.util.MultiValueMap;

/**
 * SolrSearchQueryFactory implementation
 *
 * @author ebrimatunkara
 */
public class SolrSearchQueryFactory implements SearchQueryFactory<Query, MultiValueMap> {
    private static final Logger logger = LoggerFactory.getLogger(SolrSearchQueryFactory.class);

    @Override
    public Query createQuery(MultiValueMap params) {
        return prepareQuery(params);
    }

    @Override
    public Query createQuery(MultiValueMap params, Pageable page) {
        Query query = prepareQuery(params);
        query.setPageRequest(page);
        return query;
    }

    private Query prepareQuery(MultiValueMap params) {
        Query query = new SimpleQuery();
        params.entrySet().stream().forEach(e->{
            Entry entry = (Entry)e;
            String key = entry.getKey().toString();
            List value = (List)entry.getValue();
            boolean added = false;
            if(key.equals(Searchable.TYPE_FIELD) && value.size() > 0){
                String klazzName = value.get(0).toString();    
                if(!DataUtils.classTypeExists(value.get(0).toString())){
                    String val = DataUtils.mapClassName(Searchable.class, klazzName);
                    query.addCriteria(new Criteria(key).in(val));
                    added = true;
                }
            }
            if(!added){
                query.addCriteria(new Criteria(key).in(value));
             }
        });
        logger.info(params.toSingleValueMap().toString());
        return query;
    }

}
