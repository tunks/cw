/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Rank;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author ebrimatunkara
 */
public interface RankRepository extends PagingAndSortingRepository<Rank, Long>{
       public Long countByRankType(String rankType);
}
