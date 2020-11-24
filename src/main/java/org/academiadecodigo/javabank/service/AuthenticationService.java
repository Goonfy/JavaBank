package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

public interface AuthenticationService {

    boolean authenticate(Integer id);
    Customer getAccessingCustomer();
}
