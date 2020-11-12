package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;

public abstract class CustomerController implements Controller {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    public CustomerController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
