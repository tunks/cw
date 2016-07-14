package com.att.cw.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "USER", indexes = {
    @Index(columnList = "EMAILID", name = "user_email_index")
})
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "EMAILID", length = 200, unique = true)
    @NotNull
    @Size(min = 2, max = 200)
    private String emailId;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "NAME", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String name;

    /*@Column(name = "COUNTRYCODE", length = 4)
    @NotNull
    @Size(min = 1, max = 4)
    private String countryCode;
    
    @Column(name = "PHONE", length = 15)
    @NotNull
    @Size(min = 5, max = 15)
    private String phone;*/
    @Column(name = "ENABLED", columnDefinition = "bit default 0")
    @NotNull
    private Boolean enabled;

    /* @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {
                @JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }*/
}
