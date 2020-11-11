package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

public interface AccountServiceInterface {

    Account add(AccountType accountType);
    void deposit(int id, double amount);
    void withdraw(int id, double amount);
    void transfer(int srcId, int dstId, double amount);
}
