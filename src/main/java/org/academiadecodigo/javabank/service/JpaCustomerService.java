package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JpaCustomerService implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public JpaCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void add(Customer customer) {
        customerDao.saveOrUpdate(customer);
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

}
