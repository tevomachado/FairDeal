package com.fairdeal.dao;

import com.com.fairdeal.entity.User;

import java.util.List;

/**
 * Created by Carlos Eduardo on 16/11/2015.
 */
public interface InterfaceDAO<T> {

    public String add(T object);
    public boolean remove(Long id);
    public boolean update(T object);
    public T get(Long id);
    public List<T> getAll();

}
