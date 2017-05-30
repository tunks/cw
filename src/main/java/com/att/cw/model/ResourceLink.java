/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ebrimatunkara
 */
@Entity
@Table(name="RESOURCE_LINK")
public class ResourceLink extends Component{
    private Long modelId;
    private String modelAttribute;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
      
    
}
