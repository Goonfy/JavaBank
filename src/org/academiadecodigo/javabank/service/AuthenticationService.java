package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

public class AuthenticationService implements AuthenticationServiceInterface {
    @Override
    public boolean authenticate(Integer id) {
        return false;
    }

    @Override
    public Customer getAccessingCustomer() {
        return null;
    }
}
