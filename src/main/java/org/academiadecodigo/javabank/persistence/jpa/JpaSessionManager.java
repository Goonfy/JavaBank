package org.academiadecodigo.javabank.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class JpaSessionManager {

    @PersistenceUnit
    private EntityManagerFactory emf; // the persistence unit
    private EntityManager em; // the persistence context

    public JpaSessionManager() {

    }

    public void startSession() {

        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    public void stopSession() {

        if (em != null) {
            em.close();
        }

        em = null;
    }

    public EntityManager getCurrentSession() {
        startSession();
        return em;
    }
}