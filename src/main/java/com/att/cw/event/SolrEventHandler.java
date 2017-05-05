/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.event;

import com.att.cw.model.SearchableDocument;
import com.att.cw.service.SearchableDocumentService;
import com.att.cw.support.solr.ObjectToSolrDocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Solr Event handler - concrete implementation
 *
 * @author ebrimatunkara
 */
public class SolrEventHandler implements EventHandler {

    @Autowired
    private SearchableDocumentService searchableDocumentService;

    /**
     * Processing incoming event objects
     */
    @EventListener
    @Async
    @Override
    public void handle(Event event) {
        Object obj = event.getData();
        //TODO factory class
        ObjectToSolrDocumentConverter converter = new ObjectToSolrDocumentConverter(event.getExcludedFields());
        SearchableDocument doc = converter.convert(obj);
        searchableDocumentService.save(doc);
        System.out.println("Handle solr event ");
        System.out.println(event);
    }

}
