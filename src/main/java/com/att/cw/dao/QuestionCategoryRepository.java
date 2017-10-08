/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.QuestionCategory;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author ebrimatunkara
 */
public interface QuestionCategoryRepository extends PagingAndSortingRepository<QuestionCategory, Long> {

    public QuestionCategory findByCategory(String category);

    //@Query("select c.id, c.category from QuestionCategory c , COMPONENT cp WHERE c.id = cp.id order by c.category asc")
    //public List<QuestionCategory> findAllCategories();
}
