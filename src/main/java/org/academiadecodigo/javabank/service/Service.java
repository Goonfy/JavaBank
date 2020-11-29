package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface Service<T> {

    void add(T data);
    void remove(int id);

    T get(int id);
    List<T> listAll();
}
