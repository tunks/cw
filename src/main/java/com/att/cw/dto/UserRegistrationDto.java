package com.att.cw.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


public class UserRegistrationDto  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5567656478582171832L;
	
	@NotEmpty (message = "Email must not be blank!")
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="Invalid Email Format")
	private String email;
	
	@NotEmpty(message = "Password cannot be blank!")
	private String password;
	
	@NotEmpty(message = "Name cannot be blank!")
	private String Name;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
