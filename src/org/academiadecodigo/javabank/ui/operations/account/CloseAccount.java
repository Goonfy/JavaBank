package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.EditCustomerMenu;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.Viewer;
import org.academiadecodigo.javabank.ui.viewer.account.CloseAccountViewer;

public class CloseAccount extends Operation {
    public CloseAccount(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Viewer viewer = new CloseAccountViewer(getCustomerId(), getPrompt(), getMenu(), getBank());

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
