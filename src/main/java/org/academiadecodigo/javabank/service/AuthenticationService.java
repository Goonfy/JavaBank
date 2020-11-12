package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

public class AuthenticationService implements AuthenticationServiceInterface {

    private CustomerService customerService;
    private Customer accessingCustomer;

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
