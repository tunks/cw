/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.att.cw.model.Authority;
import com.att.cw.model.User;
import com.att.cw.model.UserProfile;

/**
 *
 * @author ebrimatunkara
 */
public class UserDto {

    private Long id;
    private String email;
    private Set<AuthorityDto> authorities;
    private UserProfile userProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AuthorityDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<AuthorityDto> authorities) {
		this.authorities = authorities;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public static class UserDtoBuilder {

        UserDto userDto;

        public UserDtoBuilder() {
            userDto = new UserDto();
        }

        public UserDtoBuilder setUserId(Long userId) {
            userDto.setId(userId);
            return this;
        }

        public UserDtoBuilder setUserEmail(String email) {
            userDto.setEmail(email);
            return this;
        }

        public UserDtoBuilder setUserProfile(UserProfile userProfile) {
            userDto.setUserProfile(userProfile);
            return this;
        }
        public UserDtoBuilder setAuthority(Set<Authority> authorities) {
        	
        	Set<AuthorityDto> authorityDtos = new HashSet<AuthorityDto>();
        	Iterator<Authority> it = authorities.iterator();
    		while(it.hasNext())
    		{
    			Authority auth = it.next();
    			authorityDtos.add(new AuthorityDto.AuthorityDtoBuilder().setId(auth.getId()).setName(auth.getName()).build());
    		}
    		userDto.setAuthorities(authorityDtos);
    		return this;
        }

        public UserDto build() {
            return userDto;
        }

    }

}
