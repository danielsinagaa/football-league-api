package com.enigma.Football.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService <T, ID> implements ServiceInterface<T, ID> {
    protected final JpaRepository<T, ID> repository;

    public AbstractService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
