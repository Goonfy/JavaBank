package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericJpaDao<T> implements Dao<T> {

    private final JpaSessionManager sessionManager;

    public GenericJpaDao(JpaSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public JpaSessionManager getSessionManager() {
        return sessionManager;
    }
}
