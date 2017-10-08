/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.UserDto;
import com.att.cw.dto.UserDto.UserDtoBuilder;
import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;
import com.att.cw.model.CandidateProfile;
import com.att.cw.model.User;
import com.att.cw.model.UserProfile;

/**
 *
 * @author ebrimatunkara
 */
public class UserDtoMapper {

    public static UserDto mapEntityIntoDto(User user) {
    	
        return new UserDtoBuilder()
                .setUserId(user.getId())
                .setUserEmail(user.getEmail())
                .setAuthority(user.getAuthorities())
                //.setUserProfile(user.getProfile())
                .build();
    }
}
