package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AbstractAccount;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for account management
 */
public class JpaAccountService implements AccountService {

    private final JpaAuthenticationService authenticationService;
    private final AccountDao<AbstractAccount> accountDao;
    private final JpaTransactionManager transactionManager;

    public JpaAccountService(JpaAuthenticationService authenticationService, JpaTransactionManager transactionManager, AccountDao<AbstractAccount> accountDao) {
        this.authenticationService = authenticationService;
        this.transactionManager = transactionManager;
        this.accountDao = accountDao;
    }

    @Override
    public void add(AbstractAccount account) {
        try {
            account.setCustomer(authenticationService.getAccessingCustomer());

            transactionManager.beginWrite();
            AbstractAccount acc = accountDao.saveOrUpdate(account);
            transactionManager.commit();

            authenticationService.getAccessingCustomer().addAccount(acc);
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void remove(int id) {
        try {
            AbstractAccount account = accountDao.findById(id);

            transactionManager.beginWrite();
            accountDao.delete(id);
            transactionManager.commit();

            authenticationService.getAccessingCustomer().removeAccount(account);
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void deposit(int id, double amount) {
        try {
            AbstractAccount account = accountDao.findById(id);

            if (!account.canCredit(amount)) {
                return;
            }

            account.addBalance(amount);

            transactionManager.beginWrite();
            accountDao.saveOrUpdate(account);
            transactionManager.commit();
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        try {
            AbstractAccount account = accountDao.findById(id);

            if (!account.canWithdraw() || !account.canDebit(amount)) {
                return;
            }

            account.removeBalance(amount);

            transactionManager.beginWrite();
            accountDao.saveOrUpdate(account);
            transactionManager.commit();
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAccount = accountDao.findById(srcId);
        Account dstAccount = accountDao.findById(dstId);

        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            withdraw(srcId, amount);
            deposit(dstId, amount);
        }
    }

    @Override
    public List<AbstractAccount> getAllAccountsInfoFrom(Customer customer) {
        List<AbstractAccount> accounts = new LinkedList<>();
        for (AbstractAccount acc : accountDao.findAll()) {
            if (acc.getCustomer().getId() == customer.getId()) {
                accounts.add(acc);
            }
        }

        return accounts;
    }

    public double getBalance(int id) {
        return accountDao.findById(id).getBalance();
    }

    public int getBalanceFromAllAccounts() {
        int balance = 0;

        for (Account account : accountDao.findAll()) {
            balance += account.getBalance();
        }

        return balance;
    }
}
