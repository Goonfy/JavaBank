package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface Controller<T> {

    ModelAndView listAllItems();
    ModelAndView showItem(Integer id) throws InvalidCustomerID;
    ModelAndView addItem();
    ModelAndView editItem(Integer id) throws InvalidCustomerID;
    ModelAndView removeItem(Integer id) throws InvalidCustomerID;
    ModelAndView saveItem(Integer id, T data, BindingResult bindingResult) throws InvalidCustomerID;
    ModelAndView saveItem(T data, BindingResult bindingResult) throws InvalidCustomerID;
}
