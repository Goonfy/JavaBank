package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

public interface AuthenticationService {

    boolean authenticate(Integer id);
    Customer getAccessingCustomer();
}
