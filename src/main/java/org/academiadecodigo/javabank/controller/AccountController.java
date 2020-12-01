package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping("/accounts")
public class AccountController implements Controller<AbstractAccount> {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public String listAllItems(Model model) {
        model.addAttribute("customers", accountService.listAll());

        return "accounts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Override
    public String showItem(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", accountService.get(id));

        return "accountinfo";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @Override
    public String addItem(Model model) {
        model.addAttribute("account", AbstractAccount.class);

        return "accountadd";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    @Override
    public String editItem(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.get(id));

        return "accountedit";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
    @Override
    public String removeItem(@PathVariable Integer id, Model model) {
        accountService.remove(id);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save/{id}")
    @Override
    public String saveItem(@PathVariable Integer id, @ModelAttribute AbstractAccount account) {

        //AbstractAccount acc = accountService.get(id);
        //acc.addBalance(account.getBalance());

        accountService.add(account);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @Override
    public String saveItem(@ModelAttribute AbstractAccount account) {

        accountService.add(account);

        return "redirect:/";
    }

}
