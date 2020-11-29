package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class CustomerController implements Controller {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public String show(Model model) {
        model.addAttribute("customers", customerService.listAll());

        return "customers";
    }

}
