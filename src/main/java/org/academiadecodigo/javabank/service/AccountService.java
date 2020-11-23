package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
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
    private final JpaSessionManager sessionManager;
    private final JpaTransactionManager transactionManager;

    public AccountService(AuthenticationService authenticationService, JpaTransactionManager transactionManager, JpaSessionManager sessionManager) {
        this.authenticationService = authenticationService;
        this.transactionManager = transactionManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public void add(Account account) {
        try {
            account.setCustomer(authenticationService.getAccessingCustomer());

            transactionManager.beginWrite();
            sessionManager.getCurrentSession().persist(account);
            transactionManager.commit();
        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
    }

    @Override
    public void remove(int id) {
        try {
            Account account = get(id);

            transactionManager.beginWrite();
            sessionManager.getCurrentSession().remove(sessionManager.getCurrentSession().merge(account));
            transactionManager.commit();

            authenticationService.getAccessingCustomer().removeAccount(account);
        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
    }

    @Override
    public void deposit(int id, double amount) {
        try {
            Account account = get(id);

            if (!account.canCredit(amount)) {
                return;
            }

            account.addBalance(amount);

            transactionManager.beginWrite();
            sessionManager.getCurrentSession().persist(sessionManager.getCurrentSession().merge(account));
            transactionManager.commit();

        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        try {
            Account account = get(id);

            if (!account.canWithdraw() || !account.canDebit(amount)) {
                return;
            }

            transactionManager.beginWrite();
            sessionManager.getCurrentSession().merge(account).removeBalance(amount);
            transactionManager.commit();

        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
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

    public double getBalance(int id) {
        return get(id).getBalance();
    }

    public int getBalanceFromAllAccounts() {
        int balance = 0;

        for (Account account : listAll()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public Account get(int id) {
        try {
            CriteriaBuilder builder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
            Root<Account> root = criteriaQuery.from(Account.class);
            criteriaQuery.select(root);
            criteriaQuery.where(
                    builder.equal(root.get("id"), id)
            );

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();
        } finally {
            sessionManager.stopSession();
        }
    }

    public List<Account> listAll() {
        try {
            CriteriaBuilder builder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
            Root<Account> root = criteriaQuery.from(Account.class);
            criteriaQuery.select(root);

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();
        } finally {
            sessionManager.stopSession();
        }
    }
}
