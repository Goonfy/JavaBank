package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Responsible for account management
 */
public class AccountService implements AccountServiceInterface {

    private final AuthenticationService authenticationService;
    private final AccountDao<Account> accountDao;
    private final JpaTransactionManager transactionManager;

    public AccountService(AuthenticationService authenticationService, JpaTransactionManager transactionManager, AccountDao<Account> accountDao) {
        this.authenticationService = authenticationService;
        this.transactionManager = transactionManager;
        this.accountDao = accountDao;
    }

    @Override
    public void add(Account account) {
        try {
            account.setCustomer(authenticationService.getAccessingCustomer());

            transactionManager.beginWrite();
            Account acc = accountDao.saveOrUpdate(account);
            transactionManager.commit();

            authenticationService.getAccessingCustomer().addAccount(acc);
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void remove(int id) {
        try {
            Account account = accountDao.findById(id);

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
            Account account = accountDao.findById(id);

            if (!account.canCredit(amount)) {
                return;
            }

            transactionManager.beginWrite();
            accountDao.saveOrUpdate(account).addBalance(amount);
            transactionManager.commit();
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        try {
            Account account = accountDao.findById(id);

            if (!account.canWithdraw() || !account.canDebit(amount)) {
                return;
            }

            transactionManager.beginWrite();
            accountDao.saveOrUpdate(account).removeBalance(amount);
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
