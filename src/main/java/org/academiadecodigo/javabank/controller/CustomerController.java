package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;

public abstract class CustomerController implements Controller {

    private final JpaCustomerService customerService;
    private final JpaAccountService accountService;
    private final JpaAuthenticationService authenticationService;

    public CustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    public JpaCustomerService getCustomerService() {
        return customerService;
    }

    public JpaAccountService getAccountService() {
        return accountService;
    }

    public JpaAuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
