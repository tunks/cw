/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import com.att.cw.support.solr.SearchField;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Job JobLocation
 *
 * @author ebrimatunkara
 */
@Embeddable
public class JobLocation implements Serializable {

    @NotNull
    @SearchField
    private String city;
    
    @NotNull
    @SearchField
    private String country;
    
    @SearchField
    private String locationState;
    
    @SearchField
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }
}
