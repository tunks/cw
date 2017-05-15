package com.att.cw.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.att.cw.service.SessionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AUTHORITY")
public class Authority {
	
	private static final Logger logger = LoggerFactory.getLogger(Authority.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 50, unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<User>(0);

    public Authority() {

    }

    public Authority(Long id, AuthorityName name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public Set<User> getUsers() {
    	logger.info("Authority get user called");
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
