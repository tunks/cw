package com.att.cw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * UserProfile entity model
 *
 * @author dileep
 */
@Entity
@Table(name = "DEPARTMENT_PROFILE")
@PrimaryKeyJoinColumn(name="id")
public class DepartmentProfile extends UserProfile{
	
	
	@Column(name = "name", length = 100)
    @NotNull(message = "Name Name cannot be null")
    @Size(min = 1, max = 100)
    private String name;

	
	
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
