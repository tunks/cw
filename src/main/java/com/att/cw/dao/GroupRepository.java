/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Group repository
 * @author ebrimatunkara
 */
public interface GroupRepository  extends PagingAndSortingRepository<Group, Long>{
     public Page<Group> findByName(String name, Pageable page);
}
