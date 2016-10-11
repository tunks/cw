/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted;

import com.att.cw.model.Location;
import com.att.cw.service.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * LocationController
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/countries")
public class LocationController {
    @Autowired
    private LocationService locationService;
    
    /**
     * List of countries
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Location> findCountry(){
         return locationService.findCountries();
    }
    
     /**
     * List of country states
     * @param id
     * @return 
     */
    @RequestMapping( value="{id}/states", method = RequestMethod.GET)
    public List<Location> findCountryStates(@PathVariable Long id){
         return locationService.findCountryStates(id);
    }
    
    /**
     * List of state  cities
     * @param id
     * @return 
     */
    @RequestMapping( value="{id}/cities", method = RequestMethod.GET)
    public List<Location> findStateCities(@PathVariable Long id){
         return locationService.findCountryStates(id);
    }
}
