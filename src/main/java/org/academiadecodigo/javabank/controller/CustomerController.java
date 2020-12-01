package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class CustomerController implements Controller<Customer> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public String listAllItems(Model model) {
        model.addAttribute("customers", customerService.listAll());

        return "customers";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Override
    public String showItem(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));

        return "customerinfo";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @Override
    public String addItem(Model model) {
        model.addAttribute("customer", new Customer());

        return "customeradd";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    @Override
    public String editItem(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));

        return "customeredit";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
    @Override
    public String removeItem(@PathVariable Integer id, Model model) {
        customerService.remove(id);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save/{id}")
    @Override
    public String saveItem(@PathVariable Integer id, @ModelAttribute Customer customer) {

        Customer c = customerService.get(id);
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setPhoneNumber(customer.getPhoneNumber());

        customerService.add(c);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @Override
    public String saveItem(@ModelAttribute Customer customer) {

        customerService.add(customer);

        return "redirect:/";
    }

}
