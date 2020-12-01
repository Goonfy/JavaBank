package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface Controller<T> {

    String listAllItems(Model model);
    String showItem(Integer id, Model model);
    String addItem(Model model);
    String editItem(Integer id, Model model);
    String removeItem(Integer id, Model model);
    String saveItem(Integer id, T customer);
    String saveItem(T customer);
}
