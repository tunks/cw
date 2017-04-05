package com.att.cw.controller;

/**
 * BaseController abstract class
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <ID>
 */
public interface BaseController<T, ID> {

    /**
     * Request method to find and return a resource by its id
     *
     * @param id
     * @return
     *
     */
    public T find(ID id);

    /**
     * Request method to delete all resources
     *
     */
    public void deleteAll();

    /**
     * Request method to delete a resource by its id
     *
     * @param id
     *
     */
    public void delete(ID id);

    /**
     * Request method to create resource
     *
     * @param object
     * @return
     *
     */
    public T create(T object);

    /**
     * Request method to update resource
     *
     * @param object
     * @return
     *
     */
    public T update(T object);
}
