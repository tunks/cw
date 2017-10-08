/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted;

import com.att.cw.support.SearchOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Content search rest controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/contents")
public class SearchContentController implements SearchOperation<Map> {

    /**
     * Document search service
     *
     */
    @Autowired
    private SearchOperation<Map> searchableDocumentService;

    /**
     * Search contents with query parameters and pegeable option
     *
     * @param params : MultiValueMap query params
     * @param page: Pageable options
     * @return List - list of search results contents
     *
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @Override
    public Page<Map> search(@RequestParam MultiValueMap params, Pageable page) {
        return searchableDocumentService.search(params, page);
    }

    @Override
    public Page<Map> search(MultiValueMap params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
