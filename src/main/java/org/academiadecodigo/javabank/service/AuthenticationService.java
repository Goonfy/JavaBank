package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

import javax.persistence.EntityManagerFactory;

public class AuthenticationService implements AuthenticationServiceInterface {

    private CustomerService customerService;
    private Customer accessingCustomer;

    private final EntityManagerFactory entityManagerFactory;

    public AuthenticationService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean authenticate(Integer id) {
        accessingCustomer = customerService.get(id);
        return accessingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accessingCustomer;
    }
}
