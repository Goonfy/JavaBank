package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerService implements CustomerServiceInterface {

    private final JpaTransactionManager transactionManager;
    private final CustomerDao<Customer> customerDao;

    public CustomerService(JpaTransactionManager transactionManager, CustomerDao<Customer> customerDao) {
        this.transactionManager = transactionManager;
        this.customerDao = customerDao;
    }

    @Override
    public void add(String name, String email, String phoneNumber) {
        try {
            transactionManager.beginWrite();
            customerDao.saveOrUpdate(new Customer(name, email, phoneNumber));
            transactionManager.commit();
        } finally {
            transactionManager.rollback();
        }
    }

    public void remove(int id) {
        try {
            transactionManager.beginWrite();
            customerDao.delete(id);
            transactionManager.commit();
        } finally {
            transactionManager.rollback();
        }
    }

    @Override
    public List<Customer> listAll() {
        return customerDao.findAll();
    }

    public Customer get(int id) {
        return customerDao.findById(id);
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
