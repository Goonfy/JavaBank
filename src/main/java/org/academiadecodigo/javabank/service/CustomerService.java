package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerService implements CustomerServiceInterface {

    private final EntityManagerFactory entityManagerFactory;

    public CustomerService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(String name, String email, String phoneNumber) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(new Customer(name, email, phoneNumber));
            entityManager.getTransaction().commit();

        } catch (RollbackException e) {
            Optional.ofNullable(entityManager).ifPresent(manager -> manager.getTransaction().rollback());
        } finally {
            close(entityManager);
        }
    }

    public void remove(int id) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(get(id)));
            entityManager.getTransaction().commit();

        } catch (RollbackException e) {
            Optional.ofNullable(entityManager).ifPresent(manager -> manager.getTransaction().rollback());
        } finally {
            close(entityManager);
        }
    }

    @Override
    public List<Customer> listAll() {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            close(entityManager);
        }
    }

    public Customer get(int id) {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(
                    builder.equal(root.get("id"), id)
            );

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } finally {
            close(entityManager);
        }
    }

    public String getAllCustomersInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Customer customer : listAll()) {
            stringBuilder.append(customer.toString());
        }

        return stringBuilder.toString();
    }

    public int getNumberOfCustomers() {
        return listAll().size();
    }

    private void close(EntityManager entityManager) {
        Optional.ofNullable(entityManager).ifPresent(EntityManager::close);
    }
}
