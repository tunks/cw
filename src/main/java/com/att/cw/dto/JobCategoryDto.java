/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

/**
 * JobCategoryDTO
 * @author ebrimatunkara
 */
public  class JobCategoryDto extends JobComponentDto{
    public JobCategoryDto() {
    }

    public JobCategoryDto(Long id, String name) {
        super(id, name);
    } 
}
