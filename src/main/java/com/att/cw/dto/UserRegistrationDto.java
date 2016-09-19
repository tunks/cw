package com.att.cw.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import com.att.cw.model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;


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
	
	@NotNull(message = "Date Of Birth Cannot be empty")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@NotNull(message = "Gender Cannot be empty")
	private Gender gender;
	

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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
