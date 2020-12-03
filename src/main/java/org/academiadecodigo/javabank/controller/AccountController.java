package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.controller.dto.AccountDto;
import org.academiadecodigo.javabank.controller.dto.CustomerDto;
import org.academiadecodigo.javabank.controller.dto.DtoMapper;
import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.AbstractAccount;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
@RequestMapping("/accounts")
public class AccountController implements Controller<AccountDto> {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @Override
    public ModelAndView listAllItems() {
        ModelAndView modelAndView = new ModelAndView("accounts");
        //modelAndView.addObject("accounts", DtoMapper.convertToDto(accountService.listAll()));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Override
    public ModelAndView showItem(@PathVariable Integer id) throws InvalidCustomerID {
        ModelAndView modelAndView = new ModelAndView("accountinfo");

        modelAndView.addObject("account", DtoMapper.convertToDto(accountService.get(id)));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @Override
    public ModelAndView addItem() {
        ModelAndView modelAndView = new ModelAndView("accountadd");

        //modelAndView.addObject("account", new Account());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    @Override
    public ModelAndView editItem(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("accountedit");

        modelAndView.addObject("account", DtoMapper.convertToDto(accountService.get(id)));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
    @Override
    public ModelAndView removeItem(@PathVariable Integer id) {
        accountService.remove(id);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save/{id}")
    @Override
    public ModelAndView saveItem(@PathVariable Integer id, @Valid @ModelAttribute("account") AccountDto accountDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("accountedit");
        }

        AbstractAccount account = accountService.get(id);
        account.setBalance(accountDto.getBalance());
        account.setCustomer(accountDto.getCustomer());

        accountService.add(account);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @Override
    public ModelAndView saveItem(@Valid @ModelAttribute("account") AccountDto accountDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("accountadd");
        }

        //accountService.add(DtoMapper.convertFromDto(accountDto));

        return new ModelAndView("redirect:/");
    }
}
