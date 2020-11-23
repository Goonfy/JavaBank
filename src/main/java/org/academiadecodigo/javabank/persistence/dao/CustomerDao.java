package org.academiadecodigo.javabank.persistence.dao;

public interface CustomerDao<T> extends Dao<T> {

    T findByUsername(String username);
    T findByEmail(String email);
}
