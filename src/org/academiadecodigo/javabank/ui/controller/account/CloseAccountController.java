package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.account.CloseAccountView;

public class CloseAccountController extends OperationController {
    public CloseAccountController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        CloseAccountView viewer = new CloseAccountView(getCustomerId(), getPrompt(), getMenu(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            viewer.error();
            return;
        }

        int accountId = viewer.getInput();
        if (accountId == -1) {
            viewer.error();
            return;
        }

        customer.closeAccount(customer.getAccountFromID(accountId));

        viewer.success();
    }
}
