package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * Responsible for account management
 */
public class AccountService implements AccountServiceInterface {

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
            close(entityManager);
        }
    }

    @Override
    public void remove(int id) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.remove(get(id));
            entityManager.getTransaction().commit();

        } catch (RollbackException e) {
            Optional.ofNullable(entityManager).ifPresent(manager -> manager.getTransaction().rollback());
        } finally {
            close(entityManager);
        }
    }

    @Override
    public void deposit(int id, double amount) {
        Account account = get(id);

        if (!account.canCredit(amount)) {
            return;
        }

        account.addBalance(amount);
    }

    @Override
    public void withdraw(int id, double amount) {
        Account account = get(id);

        if (!account.canWithdraw()) {
            return;
        }

        account.removeBalance(amount);
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
            Root<Account> root = criteriaQuery.from(Account.class);
            criteriaQuery.select(root);
            criteriaQuery.where(
                    builder.equal(root.get("id"), id)
            );

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } finally {
            close(entityManager);
        }
    }

    public List<Account> listAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
            Root<Account> root = criteriaQuery.from(Account.class);
            criteriaQuery.select(root);

            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
           close(entityManager);
        }
    }

    private void close(EntityManager entityManager) {
        Optional.ofNullable(entityManager).ifPresent(EntityManager::close);
    }
}
