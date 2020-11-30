package org.academiadecodigo.javabank.controller;

import org.springframework.ui.Model;

public interface Controller<T> {

    String listAllItems(Model model);
    String showItem(Integer id, Model model);
    String addItem(Model model);
    String editItem(Integer id, Model model);
    String removeItem(Integer id, Model model);
}
