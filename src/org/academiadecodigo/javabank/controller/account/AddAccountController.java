package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private final AddAccountPromptView view;

    public AddAccountController(Bank bank, Customer customer) {
        super(bank, customer);

        view = new AddAccountPromptView();
    }

    @Override
    public void execute() {
        if (getCustomer() == null) {
            view.error();
            return;
        }

        getCustomer().openAccount(AccountType.values()[view.createMenu(AccountType.values())]);

        view.success();
    }
}
