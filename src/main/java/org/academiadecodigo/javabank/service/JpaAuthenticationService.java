package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

public class JpaAuthenticationService implements AuthenticationService {

    private JpaCustomerService customerService;
    private Customer accessingCustomer;

    public void setCustomerService(JpaCustomerService customerService) {
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
