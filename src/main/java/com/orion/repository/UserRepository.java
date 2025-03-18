package com.orion.repository;

import com.orion.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository<User, Long> {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findByLogin(String login) {
        var session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT user FROM User user WHERE login = :login", User.class)
                .setParameter("login", login)
                .uniqueResultOptional();
    }
}
