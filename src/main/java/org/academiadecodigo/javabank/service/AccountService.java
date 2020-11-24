package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;

import java.util.List;

public interface AccountService {

    void add(AbstractAccount account);
    void remove(int id);
    void deposit(int id, double amount);
    void withdraw(int id, double amount);
    void transfer(int srcId, int dstId, double amount);
    List<AbstractAccount> getAllAccountsInfoFrom(Customer customer);
}
