/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * QuestionCategory entity model
 * @author ebrimatunkara
 */
@Entity
@Table(name="QUESTION_CATEGORY")
public class QuestionCategory extends Component{
    //@Column(nullable=false)
    private String category;

    public QuestionCategory() {
    }

    public QuestionCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
   
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionCategory other = (QuestionCategory) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        
        return true;
    }
    
    
}
