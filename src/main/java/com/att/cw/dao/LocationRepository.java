/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Location;
import com.att.cw.projection.LocationProjection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
    @Query("select l.id, l.name, l.locationType from Location l where l.locationType = ?1 order by l.name asc")
    public List<LocationProjection> findByLocationType(int type);
    
     /**
     * Find by locationType using pagination
     * @param type
     * @param page
     * @return
     */
    @Query("select l.id, l.name, l.locationType from Location l where l.locationType = ?1")
    public Page<LocationProjection> findByLocationType(int type, Pageable page);

    /**
     * Find by locationType and parentId
     * @param type
     * @param parentId
     * @return
     */
    @Query("select l.id, l.name, l.locationType from Location l where l.locationType = ?1 and l.parentId = ?2 order by l.name asc")
    public List<LocationProjection> findByLocationTypeAndParentId(int type, Long parentId);
   
     /**
     * Find by locationType and parentId using pagination
     * @param type
     * @param parentId
     * @param page
     * @return
     */
    @Query("select l.id, l.name, l.locationType from Location l where l.locationType = ?1 and l.parentId = ?2 order by l.name")
    public Page<LocationProjection> findByLocationTypeAndParentId(int type, Long parentId, Pageable page);
}
