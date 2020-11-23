package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerService implements CustomerServiceInterface {

    private final JpaTransactionManager transactionManager;
    private final JpaSessionManager sessionManager;

    public CustomerService(JpaTransactionManager transactionManager, JpaSessionManager sessionManager) {
        this.transactionManager = transactionManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public void add(String name, String email, String phoneNumber) {
        try {
            transactionManager.beginWrite();
            sessionManager.getCurrentSession().persist(new Customer(name, email, phoneNumber));
            transactionManager.commit();

        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
    }

    public void remove(int id) {
        try {
            transactionManager.beginWrite();
            sessionManager.getCurrentSession().remove(sessionManager.getCurrentSession().merge(get(id)));
            transactionManager.commit();

        } catch (RollbackException e) {
            transactionManager.rollback();
        } finally {
            sessionManager.stopSession();
        }
    }

    @Override
    public List<Customer> listAll() {
        try {
            CriteriaBuilder builder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();
        } finally {
            sessionManager.stopSession();
        }
    }

    public Customer get(int id) {
        try {
            CriteriaBuilder builder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(
                    builder.equal(root.get("id"), id)
            );

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();
        } finally {
            sessionManager.stopSession();
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
}
