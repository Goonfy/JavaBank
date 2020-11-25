package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for account management
 */
public class JpaAccountService implements AccountService {

    private final JpaAuthenticationService authenticationService;
    private final AccountDao<AbstractAccount> accountDao;

    public JpaAccountService(JpaAuthenticationService authenticationService, AccountDao<AbstractAccount> accountDao) {
        this.authenticationService = authenticationService;
        this.accountDao = accountDao;
    }

    @Transactional
    @Override
    public void add(AbstractAccount account) {
        account.setCustomer(authenticationService.getAccessingCustomer());

        AbstractAccount acc = accountDao.saveOrUpdate(account);

        authenticationService.getAccessingCustomer().addAccount(acc);
    }

    @Transactional
    @Override
    public void remove(int id) {
        try {
            AbstractAccount account = accountDao.findById(id);

            accountDao.delete(id);

            authenticationService.getAccessingCustomer().removeAccount(account);
        } finally {
        }
    }

    @Transactional
    @Override
    public void deposit(int id, double amount) {
        AbstractAccount account = accountDao.findById(id);

        if (!account.canCredit(amount)) {
            return;
        }

        account.addBalance(amount);

        accountDao.saveOrUpdate(account);
    }

    @Transactional
    @Override
    public void withdraw(int id, double amount) {
        AbstractAccount account = accountDao.findById(id);

        if (!account.canWithdraw() || !account.canDebit(amount)) {
            return;
        }

        account.removeBalance(amount);

        accountDao.saveOrUpdate(account);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public double getBalance(int id) {
        return accountDao.findById(id).getBalance();
    }

    @Transactional(readOnly = true)
    public int getBalanceFromAllAccounts() {
        int balance = 0;

        for (Account account : accountDao.findAll()) {
            balance += account.getBalance();
        }

        return balance;
    }
}
