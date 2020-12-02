package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.controller.dto.CustomerDto;
import org.academiadecodigo.javabank.controller.dto.DtoMapper;
import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class CustomerController implements Controller<CustomerDto> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public ModelAndView listAllItems() {
        ModelAndView modelAndView = new ModelAndView("customers");
        modelAndView.addObject("customers", customerService.listAll());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Override
    public ModelAndView showItem(@PathVariable Integer id) throws InvalidCustomerID {
        ModelAndView modelAndView = new ModelAndView("customerinfo");

        modelAndView.addObject("customer", customerService.get(id));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @Override
    public ModelAndView addItem() {
        ModelAndView modelAndView = new ModelAndView("customeradd");

        modelAndView.addObject("customer", new CustomerDto());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    @Override
    public ModelAndView editItem(@PathVariable Integer id) throws InvalidCustomerID {
        ModelAndView modelAndView = new ModelAndView("customeredit");

        modelAndView.addObject("customer", DtoMapper.convertToDto(customerService.get(id)));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
    @Override
    public ModelAndView removeItem(@PathVariable Integer id) throws InvalidCustomerID {
        customerService.remove(id);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save/{id}")
    @Override
    public ModelAndView saveItem(@PathVariable Integer id, @Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult) throws InvalidCustomerID {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customeredit");
        }

        Customer customer = customerService.get(id);
        customer.setProfilePicUrl(customerDto.getProfilePicUrl());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        customerService.add(customer);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @Override
    public ModelAndView saveItem(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult) throws InvalidCustomerID {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customeradd");
        }

        customerService.add(DtoMapper.convertToCustomer(customerDto));

        return new ModelAndView("redirect:/");
    }

}
