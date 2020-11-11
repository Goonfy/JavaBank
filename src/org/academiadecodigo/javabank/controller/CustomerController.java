package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.CustomerService;

public abstract class CustomerController implements Controller {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}
