package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class CloseAccountController extends OperationController {

    public CloseAccountController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            return;
        }

        int accountId = viewer.getInput();
        if (accountId == -1) {
            return;
        }

        customer.closeAccount(customer.getAccountFromID(accountId));

    }
}
