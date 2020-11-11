package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private final AddAccountPromptView view;

    public AddAccountController(AccountService accountService, Customer customer) {
        super(accountService, customer);

        view = new AddAccountPromptView();
    }

    @Override
    public void execute() {
        if (getAccountService() == null) {
            view.error();
            return;
        }

        getAccountService().add(AccountType.values()[view.createMenu(AccountType.values())]);

        view.success();
    }
}
