package com.att.cw.dao;

import com.att.cw.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * Job Repository interface - extending Paging,sorting and CRUD interface operations
 * See http://docs.spring.io/spring-data/jpa/docs/1.9.4.RELEASE/reference/html/
 **/
public interface JobRepository extends PagingAndSortingRepository<Job, Long>{
       /**
        * Find and return jobs by group owner id by pageable 
        * @param ownerId
        * @param page
        * @return 
        */
       Page<Job> findByOwnerId(Long ownerId,Pageable page);
}
