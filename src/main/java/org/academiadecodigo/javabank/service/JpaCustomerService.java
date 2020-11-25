package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class JpaCustomerService implements CustomerService {

    private final CustomerDao<Customer> customerDao;

    public JpaCustomerService(CustomerDao<Customer> customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void add(String name, String email, String phoneNumber) {
        customerDao.saveOrUpdate(new Customer(name, email, phoneNumber));
    }

    @Transactional
    @Override
    public void remove(int id) {
        customerDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> listAll() {
        return customerDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer get(int id) {
        return customerDao.findById(id);
    }

    @Override
    public int getNumberOfCustomers() {
        return listAll().size();
    }
}
