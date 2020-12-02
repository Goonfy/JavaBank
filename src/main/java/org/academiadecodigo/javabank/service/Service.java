package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface Service<T> {

    void add(T data) throws InvalidCustomerID;
    void remove(int id) throws InvalidCustomerID;

    T get(int id) throws InvalidCustomerID;
    List<T> listAll();
}
