package com.att.cw.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
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

/**
 * *
 * User Entity model
 *
 */
@Entity
@Table(name = "USER",
        indexes = {
            @Index(columnList = "EMAIL", name = "user_email_index")},
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"EMAIL"})}
)
public class User extends UserAudit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email", length = 200, unique = true, nullable = false)
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, max = 200)
    private String email;

    @Column(name = "password", length = 100)
    @NotNull(message = "Password cannot be null")
    @Size(min = 5, max = 100)
    private String password;

    /**
     * User account will be disabled by default until it is activated
     */
    @Column(name = "enabled", columnDefinition = "bit default 0")
    @NotNull(message = "Enabled cannot be null")
    private Boolean enabled = false;

    /* @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = {MERGE})
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {
                @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")},
       //        @JoinColumn(name = "USER_ID", nullable = false,updatable = false, referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "authority_id", nullable = false, referencedColumnName = "id")})
    private Set<Authority> authorities = new HashSet<Authority>(0);

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @NotNull(message = "User Profile cannot be null")
    private UserProfile profile;
   
    @OneToOne(cascade={MERGE,REMOVE}, mappedBy="user", orphanRemoval=true)
    private JobCandidate candidate;

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
  
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public JobCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(JobCandidate candidate) {
        this.candidate = candidate;
    }
}
