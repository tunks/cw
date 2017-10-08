/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.AuthorityRepository;
import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("authorityService")
public class AuthorityService implements CrudService<Authority, Long> {

    @Resource
    private AuthorityRepository authoRepo;

    @Override
    public Authority save(Authority object) {
         return authoRepo.save(object);
    }

    @Override
    public Authority find(Long id) {
        return authoRepo.findOne(id);
    }
    
    public Authority findByName(AuthorityName name) {
        return authoRepo.findByName(name);
    }

    @Override
    public List<Authority> findAll() {
        return (List<Authority>) authoRepo.findAll();
    }

    @Override
    public Page<Authority> findAll(Pageable page) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
       authoRepo.delete(id);
    }

    @Override
    public void delete(Authority object) {
       authoRepo.delete(object);
    }

    @Override
    public void deleteAll() {
        authoRepo.deleteAll();
    }

    @Override
    public boolean exists(Long id) {
       return authoRepo.exists(id);
    }
}
