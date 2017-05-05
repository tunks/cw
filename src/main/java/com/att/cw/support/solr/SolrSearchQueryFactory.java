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
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.AnyCriteria;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.util.MultiValueMap;

/**
 * SolrSearchQueryFactory implementation
 *
 * @author ebrimatunkara
 */
public class SolrSearchQueryFactory implements SearchQueryFactory<Query, MultiValueMap> {

    /**
     * Create query instance 
     *
     * @param params -> MultiValueMap
     * @return Query
     */
    @Override
    public Query createQuery(MultiValueMap params) {
        return prepareQuery(params);
    }

    /**
     * Create query instance with Pageable
     *
     * @param params -> MultiValueMap
     * @param page -> Pageable
     * @return Query
     */
    @Override
    public Query createQuery(MultiValueMap params, Pageable page) {
        Query query = prepareQuery(params);
        query.setPageRequest(page);
        return query;
    }

    /**
     * Prepare query criteria
     *
     * @param params MultiValueMap
     * @return Query
     */
    private Query prepareQuery(MultiValueMap params) {
        SimpleQuery query = new SimpleQuery();
        if (params.isEmpty()) {
            query.addCriteria(AnyCriteria.any());
        } else {
            params.entrySet().stream().forEach(e -> {
                Entry entry = (Entry) e;
                String key = entry.getKey().toString();
                List value = (List) entry.getValue();
                boolean added = false;
                //query key is doc class type
                if (key.equals(Searchable.TYPE_FIELD) && value.size() > 0) {
                    String klazzName = value.get(0).toString();
                    if (!DataUtils.classTypeExists(value.get(0).toString())) {
                        String val = DataUtils.mapClassName(Searchable.class, klazzName);
                        query.addCriteria(new Criteria(key).in(val));
                        added = true;
                    }
                }
                //query key is "query" applies to all fields
                if (key.toLowerCase().equals("query")) {
                    query.addCriteria(new SimpleStringCriteria(value.get(0).toString()));
                    added = true;
                }
                //add other criteria
                if (!added) {
                    query.addCriteria(new Criteria(key).in(value));
                }
            });
        }
        return query;
    }

}
