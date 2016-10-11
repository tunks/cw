/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.LocationRepository;
import com.att.cw.model.Location;
import com.att.cw.support.LocationType;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("locationService")
public class LocationService {
    @Resource
    private LocationRepository locationRepository;

    /**
     * Find countries
     * @return
     */
    public List<Location> findCountries() {
        return locationRepository.findByLocationType(LocationType.COUNTRY.getIndex());
    }

    /**
     * Find country states
     * @param parentId
     * @return
     */
    public List<Location> findCountryStates(Long parentId) {
        return locationRepository.findByLocationTypeAndParentId(LocationType.STATE.getIndex(), parentId);
    }
    
    /**
     * Find states cities
     * @param parentId
     * @return
     */
    public List<Location> findStateCities(Long parentId) {
        return locationRepository.findByLocationTypeAndParentId(LocationType.CITY.getIndex(), parentId);
    }
}
