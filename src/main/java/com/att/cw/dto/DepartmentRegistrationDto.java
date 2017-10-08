package com.att.cw.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import com.att.cw.model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DepartmentRegistrationDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5567656478582171832L;

    @NotEmpty(message = "Email must not be blank!")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid Email Format")
    private String email;

    @NotEmpty(message = "Password cannot be blank!")
    private String password;

    @NotEmpty(message = "Name cannot be blank!")
    private String name;

    @NotEmpty(message = "Country cannot be blank!")
    private String country;

    @NotNull(message = "phone number cannot be empty")
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

}
