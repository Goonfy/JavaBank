package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class CloseAccountController extends OperationController {

    private final Customer customer;

    public CloseAccountController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        int accountId = getView().createAccountMenu(customer);
        if (accountId == -1) {
            getView().error();
            return;
        }

        customer.closeAccount(customer.getAccountManager().getAccountFromID(accountId));
        getView().success();
    }
}
