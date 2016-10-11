/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Location;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Location repository
 * @author ebrimatunkara
 */
@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
    /**
     * Find by locationType
     * @param type
     * @return
     */
    public List<Location> findByLocationType(int type);

    /**
     * Find by locationType and parentId
     * @param type
     * @param parentId
     * @return
     */
    public List<Location> findByLocationTypeAndParentId(int type, Long parentId);
}
