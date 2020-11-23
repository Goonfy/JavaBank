package org.academiadecodigo.javabank.persistence.jpa;

import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

public class JpaTransactionManager implements TransactionManager {

    private final JpaSessionManager sessionManager;

    public JpaTransactionManager(JpaSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void beginRead() {
        sessionManager.startSession();
    }

    @Override
    public void beginWrite() {
        sessionManager.getCurrentSession().getTransaction().begin();
    }

    @Override
    public void commit() {

        if (sessionManager.getCurrentSession().getTransaction().isActive()) {
            sessionManager.getCurrentSession().getTransaction().commit();
        }
        sessionManager.stopSession();
    }

    @Override
    public void rollback() {

        if (sessionManager.getCurrentSession().getTransaction().isActive()) {
            sessionManager.getCurrentSession().getTransaction().rollback();
        }
        sessionManager.stopSession();
    }
}
