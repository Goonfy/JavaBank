package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController extends AbstractController {
    private AccountService accountService;

    @RequestMapping
    @Override
    public String show(Model model) {
        model.addAttribute("accounts", accountService.listAll());

        return "accounts";
    }

    @Autowired
    public AccountService getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
