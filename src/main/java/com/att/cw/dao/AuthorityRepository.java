package com.att.cw.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;


public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long>
{
	
	/**
     * Find Authority by AuthorityName
     *
     * @param email
     * @param name
     * @return
     */
	public Authority  findByName(AuthorityName name);

}
