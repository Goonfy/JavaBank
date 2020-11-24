package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import java.util.List;

public class JpaCustomerService implements CustomerService {

    private final JpaTransactionManager transactionManager;
    private final CustomerDao<Customer> customerDao;

    public JpaCustomerService(JpaTransactionManager transactionManager, CustomerDao<Customer> customerDao) {
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

    @Override
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

    @Override
    public Customer get(int id) {
        return customerDao.findById(id);
    }

    @Override
    public int getNumberOfCustomers() {
        return listAll().size();
    }
}
