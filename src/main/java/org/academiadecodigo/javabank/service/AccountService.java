package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AbstractAccount;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

import java.util.List;

public interface AccountService {

    void add(AbstractAccount account);
    void remove(int id);
    void deposit(int id, double amount);
    void withdraw(int id, double amount);
    void transfer(int srcId, int dstId, double amount);
    List<AbstractAccount> getAllAccountsInfoFrom(Customer customer);
}
