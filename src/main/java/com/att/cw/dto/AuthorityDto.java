
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import java.util.HashSet;
import java.util.Set;

import com.att.cw.model.AuthorityName;
import com.att.cw.model.User;

/**
 *
 * @author Dileep
 */
public class AuthorityDto {
    private Long id;
    private AuthorityName name;
    private Set<UserDto> users = new HashSet<UserDto>(0);
  

  
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



	public Set<UserDto> getUsers() {
		return users;
	}



	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}



	public static class AuthorityDtoBuilder {

		AuthorityDto authorityDto;

        public AuthorityDtoBuilder() {
        	authorityDto = new AuthorityDto();
        }

        public AuthorityDtoBuilder setId(Long userId) {
        	authorityDto.setId(userId);
            return this;
        }

      

        public AuthorityDtoBuilder setName(AuthorityName name) {
        	authorityDto.setName(name);
            return this;
        }
        public AuthorityDtoBuilder setUser(Set<UserDto> users) {
        	authorityDto.setUsers(users);
            return this;
        }

        public AuthorityDto build() {
            return authorityDto;
        }

    }

}
