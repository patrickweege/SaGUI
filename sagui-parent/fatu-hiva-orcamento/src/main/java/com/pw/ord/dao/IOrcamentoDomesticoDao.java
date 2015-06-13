package com.pw.ord.dao;

import java.util.List;

public interface IOrcamentoDomesticoDao<T> {

    public T getByID(Long id);

    public T getNew();

    public T save(T entity);

    public List<T> getAll();

    public void delete(Long id);

}
