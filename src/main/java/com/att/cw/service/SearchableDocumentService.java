/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.solr.CustomSolrRepository;
import com.att.cw.model.SearchableDocument;
import com.att.cw.support.SearchOperation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * SearchableDocument service component
 *
 * @author ebrimatunkara
 */
@Service("searchableDocumentService")
public class SearchableDocumentService implements CrudService<SearchableDocument, Long>, SearchOperation<Map> {

    @Autowired
    private CustomSolrRepository<SearchableDocument, Long> searchRepository;

    @Autowired
    private Converter<SearchableDocument, Map> documentToObjectConverter;

    @Override
    public SearchableDocument save(SearchableDocument object) {
        return searchRepository.save(object);
    }

    @Override
    public SearchableDocument find(Long id) {
        return searchRepository.findOne(id);
    }

    @Override
    public List<SearchableDocument> findAll() {
        return (List<SearchableDocument>) searchRepository.findAll();
    }

    @Override
    public Page<SearchableDocument> findAll(Pageable page) {
        return searchRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        searchRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        searchRepository.deleteAll();
    }

    @Override
    public boolean exists(Long id) {
        return searchRepository.exists(id);
    }

    /**
     * Search for contents with query parameters and pageable conditions
     *
     * @param params
     * @param page
     * @return
     */
    @Override
    public Page<Map> search(MultiValueMap params, Pageable page) {
        Page<SearchableDocument> result = searchRepository.search(params, page);
        return result.map(documentToObjectConverter);
    }

      /**
     * Search for contents with query parameters
     *
     * @param params
     * @return
     */
    @Override
    public Page<Map> search(MultiValueMap params) {
        Page<SearchableDocument> result = searchRepository.search(params);
        return result.map(documentToObjectConverter);
    }

}
