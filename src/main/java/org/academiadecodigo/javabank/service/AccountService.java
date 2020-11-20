package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Responsible for account management
 */
public class AccountService implements AccountServiceInterface {

    private Customer customer;

    private final EntityManagerFactory entityManagerFactory;

    public AccountService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(Account account) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();

        } catch (RollbackException e) {
            Optional.ofNullable(entityManager).ifPresent(manager -> manager.getTransaction().rollback());
        } finally {
            closeEntityManager(entityManager);
        }
    }

    @Override
    public void close(int id) {
        customer.removeAccount(id);
    }

    @Override
    public void deposit(int id, double amount) {
        Account account = customer.getAccount(id);

        if (account.canCredit(amount)) {
            customer.getAccount(id).addBalance(amount);
        }
    }

    @Override
    public void withdraw(int id, double amount) {

        Account account = customer.getAccount(id);

        if (!account.canWithdraw()) {
            return;
        }

        customer.getAccount(id).addBalance(amount);
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = customer.getAccount(srcId);
        Account dstAccount = customer.getAccount(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            withdraw(srcId, amount);
            deposit(dstId, amount);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBalanceFromAllAccounts() {
        int balance = 0;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.get; // always the primary key
        } finally {
            closeEntityManager(entityManager);
        }

        /*for (Account account : accounts.values()) {
            balance += account.getBalance();
        }*/

        return balance;
    }

    private void closeEntityManager(EntityManager entityManager) {
        Optional.ofNullable(entityManager).ifPresent(EntityManager::close);
    }
}
