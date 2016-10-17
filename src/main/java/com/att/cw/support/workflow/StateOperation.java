/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.workflow;

/**
 * State Action interface
 * @author ebrimatunkara
 * @param <T>
 */
public interface StateOperation<T> {
      /**
       * StateEvent action
     * @param object
       */
      void action(T object);
}
