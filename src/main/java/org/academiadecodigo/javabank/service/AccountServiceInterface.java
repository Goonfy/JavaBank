package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

public interface AccountServiceInterface {

    Account add(Customer customer, AccountType accountType);
    void deposit(Customer customer, int id, double amount);
    void withdraw(Customer customer, int id, double amount);
    void transfer(Customer customer, int srcId, int dstId, double amount);
}
