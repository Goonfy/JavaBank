package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

public abstract class CostumerController implements Controller {

    private final Bank bank;

    public CostumerController(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
