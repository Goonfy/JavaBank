package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping("/accounts")
public class AccountController implements Controller<AbstractAccount> {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ModelAndView listAllItems() {
        return null;
    }

    @Override
    public ModelAndView showItem(Integer id) throws InvalidCustomerID {
        return null;
    }

    @Override
    public ModelAndView addItem() {
        return null;
    }

    @Override
    public ModelAndView editItem(Integer id) {
        return null;
    }

    @Override
    public ModelAndView removeItem(Integer id) {
        return null;
    }

    @Override
    public ModelAndView saveItem(Integer id, AbstractAccount customer, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ModelAndView saveItem(AbstractAccount customer, BindingResult bindingResult) {
        return null;
    }
}
