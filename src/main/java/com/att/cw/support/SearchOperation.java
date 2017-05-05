/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

/**
 * Search Operation base interface
 *
 * @author ebrimatunkara
 * @param <T>
 */
public interface SearchOperation<T> {

    /**
     * Search for contents with query parameters and pageable conditions
     *
     * @param params
     * @param page
     * @return
     */
    public Page<T> search(MultiValueMap params, Pageable page);

    /**
     * Search for contents with query parameters
     *
     * @param params
     * @return
     */
    public Page<T> search(MultiValueMap params);
}
