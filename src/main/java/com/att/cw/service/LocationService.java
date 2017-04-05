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
import com.att.cw.dto.LocationDto;

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
     *
     * @return
     */
    public List<LocationDto> findCountries() {
        return locationRepository.findByLocationType(LocationType.COUNTRY.getIndex());
    }

    /**
     * Find country states
     *
     * @param parentId
     * @return
     */
    public List<LocationDto> findCountryStates(Long parentId) {
        return locationRepository.findByLocationTypeAndParentId(LocationType.STATE.getIndex(), parentId);
    }

    /**
     * Find country states by country name
     *
     * @param name
     * @return
     */
    public List<LocationDto> findCountryStatesByName(String name) {
        return locationRepository.findByLocationTypeAndParentName(LocationType.STATE.getIndex(), name);
    }

    /**
     * Find country state cities by name
     *
     * @param state
     * @return
     */
    public List<LocationDto> findCountryStateCitiesByName(String state) {
        System.out.println("state --------------------" + state);
        return locationRepository.findByLocationTypeAndParentName(LocationType.CITY.getIndex(), state);
    }

    /**
     * Find states cities
     *
     * @param parentId
     * @return
     */
    public List<LocationDto> findStateCities(Long parentId) {
        return locationRepository.findByLocationTypeAndParentId(LocationType.CITY.getIndex(), parentId);
    }

}
