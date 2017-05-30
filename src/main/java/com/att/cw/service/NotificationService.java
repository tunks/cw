/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

/**
 *
 * @author ebrimatunkara
 * @param <R>
 * @param <S>
 */
public interface NotificationService<R, S> {

    public void send(R recipients, String subject, String content);

    public void send(S sender, R recipients, String subject, String content);

}
