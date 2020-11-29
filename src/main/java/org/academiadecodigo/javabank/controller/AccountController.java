package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping("/accounts")
public class AccountController implements Controller {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public String show(Model model) {
        model.addAttribute("accounts", accountService.listAll());

        return "accounts";
    }

}
