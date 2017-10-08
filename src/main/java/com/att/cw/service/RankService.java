/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.RankRepository;
import com.att.cw.model.Rank;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("rankService")
public class RankService  implements CrudService<Rank, Long>{
    @Resource
    private RankRepository rankRepository;
   
    @Override
    public Rank save(Rank object) {
       return rankRepository.save(object);
    }

    @Override
    public Rank find(Long id) {
       return rankRepository.findOne(id);
    }

    @Override
    public List<Rank> findAll() {
         return (List<Rank>) rankRepository.findAll();
    }

    @Override
    public Page<Rank> findAll(Pageable page) {
        return rankRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
       rankRepository.delete(id);
    }

    @Override
    public void delete(Rank object) {
       rankRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        rankRepository.deleteAll();
    }

    @Override
    public boolean exists(Long id) {
       return rankRepository.exists(id);
    }
    
    public synchronized Long  countByRankType(String rankType){
       return  rankRepository.countByRankType(rankType);
    }
   
}
