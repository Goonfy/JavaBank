package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.AccountService;

public abstract class AccountController implements Controller {

    private final AccountService accountService;
    private final Customer customer;

    public AccountController(AccountService accountService, Customer customer) {
        this.accountService = accountService;
        this.customer = customer;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public Customer getCustomer() {
        return customer;
    }
}
