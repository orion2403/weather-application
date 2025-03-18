package com.orion.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, T> {

    E save(E entity);

    List<E> findAll();

    Optional<E> findById(T id);

    void update(E e);

    boolean delete(E e);

}
