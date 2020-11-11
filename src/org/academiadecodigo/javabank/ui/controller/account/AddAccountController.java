package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.AddAccountPromptView;

public class AddAccountController extends OperationController {

    private final Customer customer;
    private final AddAccountPromptView view;

    public AddAccountController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new AddAccountPromptView();
    }

    @Override
    public void execute() {
        if (customer == null) {
            view.error();
            return;
        }

        customer.openAccount(AccountType.values()[view.createMenu(AccountType.values())]);

        view.success();
    }
}
