/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

/**
 * '0:country,1:state,2:city' Location type
 *
 * @author ebrimatunkara
 */
public enum LocationType {
    COUNTRY(0),
    STATE(1),
    CITY(2);

    /**
     * *
     */
    private final int index;

    LocationType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
