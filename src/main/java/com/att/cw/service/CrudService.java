package com.att.cw.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CRUD Service interface
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <ID>
 */
public interface CrudService<T, ID extends Serializable> {

    public T save(T object);

    public T find(ID id);

    public List<T> findAll();

    public Page<T> findAll(Pageable page);

    public void delete(ID id);

    public void delete(T object);

    public void deleteAll();

    public boolean exists(ID id);
}
