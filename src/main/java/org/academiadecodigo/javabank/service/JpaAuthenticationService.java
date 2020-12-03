package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean authenticate(Integer id) throws InvalidCustomerID {
        accessingCustomer = customerService.get(id);
        return accessingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return accessingCustomer;
    }
}
