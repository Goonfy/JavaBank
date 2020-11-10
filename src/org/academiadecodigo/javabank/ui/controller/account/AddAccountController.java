package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class AddAccountController extends OperationController {

    private final Customer customer;

    public AddAccountController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        if (customer == null) {
            getView().error();
            return;
        }

        customer.openAccount(AccountType.values()[getView().createMenu(AccountType.values())]);

        getView().success();
    }
}
