package com.orion.repository;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseRepository<E, T extends Serializable> implements CrudRepository<E, T> {

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    public BaseRepository(SessionFactory sessionFactory, Class<E> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public E save(E entity) {
        var session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public List<E> findAll() {
        return List.of();
    }

    @Override
    public Optional<E> findById(T id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public void update(E e) {
        var session = sessionFactory.getCurrentSession();
        session.merge(e);
    }

    @Override
    public boolean delete(E e) {
        return false;
    }
}
