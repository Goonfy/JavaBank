package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer get(int id);
    void remove(int id);
    List<Customer> listAll();
    void add(String name, String email, String phoneNumber);
    int getNumberOfCustomers();
}