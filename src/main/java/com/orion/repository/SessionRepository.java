package com.orion.repository;

import com.orion.model.Session;
import com.orion.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepository extends BaseRepository<Session, Long> {

    private final SessionFactory sessionFactory;

    public SessionRepository(SessionFactory sessionFactory, SessionFactory sessionFactory1) {
        super(sessionFactory, Session.class);
        this.sessionFactory = sessionFactory1;
    }
}
