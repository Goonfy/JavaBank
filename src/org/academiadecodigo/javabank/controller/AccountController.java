package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

public abstract class AccountController implements Controller {

    private final Bank bank;
    private final Customer customer;

    public AccountController(Bank bank, Customer customer) {
        this.bank = bank;
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }
}
