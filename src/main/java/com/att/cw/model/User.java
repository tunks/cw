package com.att.cw.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/***
 * User Entity model
 **/
    @Entity
@Table(name = "USER",
       indexes = {
            @Index(columnList = "EMAIL", name = "user_email_index")},
       uniqueConstraints={@UniqueConstraint(columnNames={"EMAIL"})}
     )
public class User extends UserAudit {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "EMAIL", length = 200, unique = true,nullable=false )
    @NotNull(message="Email cannot be null")
    @Size(min = 2, max = 200)
    private String email;

    @Column(name = "PASSWORD", length = 100)
    @NotNull(message="Password cannot be null")
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "NAME", length = 100)
    @NotNull(message="Name cannot be null")
    @Size(min = 4, max = 100)
    private String name;
    
    
    @Column(name = "DOB")
    @NotNull(message="dateOfBirth cannot be null")
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    @NotNull(message="dateOfBirth cannot be null")
    private Gender gender;
   
    /**
     * User account will be disabled by default until it is activated
     */
    @Column(name = "ENABLED", columnDefinition = "bit default 0")
    @NotNull(message="Enabled cannot be null")
    private Boolean enabled= false;

    /* @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {
                @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "AUTHORITY_ID", nullable = false, referencedColumnName = "ID")})
    private Set<Authority> authorities = new HashSet<Authority>(0);

    @OneToOne(mappedBy = "user")
    private UserProfile profile;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
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
