package com.enigma.Football.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ServiceInterface<T, ID> {
    public T save(T entity);

    public List<T> findAll();
}
