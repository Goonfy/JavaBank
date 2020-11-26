package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController extends AbstractController {
    private CustomerService customerService;

    @Autowired
    public CustomerService getCustomerService() {
        return customerService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping
    @Override
    public String show(Model model) {
        model.addAttribute("customers", customerService.listAll());

        return "showcustomers";
    }
}
