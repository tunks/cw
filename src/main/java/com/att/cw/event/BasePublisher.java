/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.event;

/**
 *
 * @author ebrimatunkara
 * @param <E>
 */
public interface BasePublisher<E> {
      public void publish(E event);
}
