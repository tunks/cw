/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dto.mappers;

/**
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <E>
 */
public interface BaseEntityMapper<T,E> {
      public T map(E entity);
}
