/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Location Entity
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    //`location_type` tinyint(1) NOT NULL COMMENT '0:country,1:state,2:city',
    @Column(name = "location_type", nullable = false)
    private int locationType;

    //`parent_id` int(11) NOT NULL COMMENT 'parent location_id',
    @Column(name = "parent_id", nullable = false)
    private Long parentId;
    //  `is_visible` tinyint(1) NOT NULL COMMENT '0:visible,1:invisible',
    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
