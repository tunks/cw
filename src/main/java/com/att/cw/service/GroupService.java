/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.GroupRepository;
import com.att.cw.model.Group;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * GroupService
 *
 * @author ebrimatunkara
 */
@Service("groupService")
public class GroupService implements CrudService<Group, Long> {

    @Resource
    private GroupRepository groupRepository;

    /**
     * Save group object
     *
     * @param object
     * @return
     */
    @Override
    public Group save(Group object) {
        return groupRepository.save(object);
    }

    /**
     * Find group by id
     *
     * @param id
     * @return
     */
    @Override
    public Group find(Long id) {
        return groupRepository.findOne(id);
    }

    /**
     * Get list of all groups
     *
     * @return
     */
    @Override
    public List<Group> findAll() {
        return (List<Group>) groupRepository.findAll();
    }

    /**
     * Get list of groups by pagination, default page size is 20
     *
     * @param page
     * @return, page of groups
     */
    @Override
    public Page<Group> findAll(Pageable page) {
        return groupRepository.findAll(page);
    }

    /**
     * Delete group by id
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
