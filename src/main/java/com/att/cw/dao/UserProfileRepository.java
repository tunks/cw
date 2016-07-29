/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserProfile Repository
 * @author ebrimatunkara
 */
public interface UserProfileRepository  extends PagingAndSortingRepository<UserProfile, Long>{
    
}