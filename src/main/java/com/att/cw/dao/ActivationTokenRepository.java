/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.ActivationToken;
import com.att.cw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ActivationToken repository
 * @author ebrimatunkara
 */
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long>{
    /**
     *  Find activation token by token 
     * @param token
     * @return 
     */
    public ActivationToken findByToken(String token);
    /**
     * Find activation token by user
     * @param user
     * @return 
     */
    public ActivationToken findByUser(User user);
}
