package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.AbstractAccount;

import java.util.List;

public interface AccountService extends Service<AbstractAccount> {

    void deposit(int id, double amount);
    void withdraw(int id, double amount);
    void transfer(int srcId, int dstId, double amount);

    List<AbstractAccount> getAllAccountsInfoFrom(Customer customer);
    double getBalance(int id);
    int getBalanceFromAllAccounts();
}
