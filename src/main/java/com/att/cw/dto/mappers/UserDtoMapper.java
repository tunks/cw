/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

import com.att.cw.dto.UserDto;
import com.att.cw.dto.UserDto.UserDtoBuilder;
import com.att.cw.model.User;

/**
 *
 * @author ebrimatunkara
 */
public class UserDtoMapper {

    public static UserDto mapEntityIntoDto(User user) {
        return new UserDtoBuilder()
                .setUseId(user.getId())
                .setUserEmail(user.getEmail())
                .setUsername(user.getName())
                .build();
    }
}
