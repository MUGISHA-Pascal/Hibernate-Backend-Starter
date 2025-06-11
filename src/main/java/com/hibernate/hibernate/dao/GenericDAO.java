package com.hibernate.hibernate.dao;

import java.util.List;

public interface GenericDAO<T> {
    // Create
    void save(T entity);
    
    // Read
    T findById(Long id);
    List<T> findAll();
    
    // Update
    void update(T entity);
    
    // Delete
    void delete(T entity);
    void deleteById(Long id);
} 