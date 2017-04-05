package com.att.cw.dao;

import com.att.cw.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Job Repository interface - extending Paging,sorting and CRUD interface
 * operations Spring data -
 * http://docs.spring.io/spring-data/jpa/docs/1.9.4.RELEASE/reference/html/
 *
 */
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {

    /**
     * Find job by question id
     *
     * @param id
     * @return //
     */
    @Query("select j from Job j INNER JOIN j.questions q WHERE q.id = (:id)")
    public Job findByQuestionId(@Param("id") Long id);
}
