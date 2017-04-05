/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.model.Location;

/**
 * LocationDto
 *
 * @author ebrimatunkara
 */
public interface LocationDto {

    Long getId();

    String getName();

    int getLocationType();
}
