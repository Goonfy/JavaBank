package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.AbstractAccount;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Responsible for account management
 */
@Service
public class JpaAccountService implements AccountService {

    private final JpaAuthenticationService authenticationService;
    private final AccountDao accountDao;

    @Autowired
    public JpaAccountService(JpaAuthenticationService authenticationService, AccountDao accountDao) {
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
        AbstractAccount account = get(id);

        accountDao.delete(id);

        authenticationService.getAccessingCustomer().removeAccount(account);
    }

    @Transactional(readOnly = true)
    @Override
    public AbstractAccount get(int id) {
        return accountDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AbstractAccount> listAll() {
        return accountDao.findAll();
    }

    @Transactional
    @Override
    public void deposit(int id, double amount) {
        AbstractAccount account = get(id);

        if (!account.canCredit(amount)) {
            return;
        }

        account.addBalance(amount);

        accountDao.saveOrUpdate(account);
    }

    @Transactional
    @Override
    public void withdraw(int id, double amount) {
        AbstractAccount account = get(id);

        if (!account.canWithdraw() || !account.canDebit(amount)) {
            return;
        }

        account.removeBalance(amount);

        accountDao.saveOrUpdate(account);
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAccount = get(srcId);
        Account dstAccount = get(dstId);

        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            withdraw(srcId, amount);
            deposit(dstId, amount);
        }
    }

    @Override
    public List<AbstractAccount> getAllAccountsInfoFrom(Customer customer) {
        List<AbstractAccount> accounts = new LinkedList<>();
        for (AbstractAccount acc : listAll()) {
            if (acc.getCustomer().getId() == customer.getId()) {
                accounts.add(acc);
            }
        }

        return accounts;
    }

    @Transactional(readOnly = true)
    @Override
    public double getBalance(int id) {
        return get(id).getBalance();
    }

    @Transactional(readOnly = true)
    @Override
    public int getBalanceFromAllAccounts() {
        int balance = 0;

        for (Account account : listAll()) {
            balance += account.getBalance();
        }

        return balance;
    }
}
