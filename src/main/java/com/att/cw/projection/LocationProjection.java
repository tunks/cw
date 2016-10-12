/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.projection;

import com.att.cw.model.Location;
import org.springframework.data.rest.core.config.Projection;

/**
 * LocationProjection
 * @author ebrimatunkara
 */
@Projection(name = "location" , types = Location.class)
public interface LocationProjection {
    Long getId();
    String getName();
    int getLocationType();
}
