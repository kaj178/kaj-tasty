package com.kaj.app.dao;

public interface Repository<T, ID>{
    T save(T entity);
    T findById(ID id);
    Iterable<T> findAll();
    void delete(T entity);
    void deleteById(ID id);
    T update(T entity);
}
