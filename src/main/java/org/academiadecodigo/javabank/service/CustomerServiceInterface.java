package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

import java.util.Set;

public interface CustomerServiceInterface {

    Customer get(int id);
    Set<Customer> list();
    Set<Integer> listCustomerAccountIds(Integer id);
    double getBalance(int customerId);
    void add(Customer customer);

}
