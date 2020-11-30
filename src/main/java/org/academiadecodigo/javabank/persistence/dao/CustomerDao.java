package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.Customer;

public interface CustomerDao extends Dao<Customer> {

    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
