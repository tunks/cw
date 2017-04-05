/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.QuestionType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * QuestionType repository
 *
 * @author ebrimatunkara
 */
public interface QuestionTypeRepository extends PagingAndSortingRepository<QuestionType, Long> {

    public QuestionType findByName(String name);
}
