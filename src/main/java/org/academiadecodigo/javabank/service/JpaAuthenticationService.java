package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class JpaAuthenticationService implements AuthenticationService {

    private final CustomerService customerService;
    private Customer accessingCustomer;

    @Autowired
    public JpaAuthenticationService(CustomerService customerService) {
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
