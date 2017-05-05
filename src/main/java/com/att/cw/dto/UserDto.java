/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto;

import com.att.cw.model.User;

/**
 *
 * @author ebrimatunkara
 */
public class UserDto {

    private Long id;
    private String username;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class UserDtoBuilder {

        UserDto userDto;

        public UserDtoBuilder() {
            userDto = new UserDto();
        }

        public UserDtoBuilder setUseId(Long userId) {
            userDto.setId(userId);
            return this;
        }

        public UserDtoBuilder setUserEmail(String email) {
            userDto.setEmail(email);
            return this;
        }

        public UserDtoBuilder setUsername(String username) {
            userDto.setUsername(username);
            return this;
        }

        public UserDto build() {
            return userDto;
        }

    }

}
