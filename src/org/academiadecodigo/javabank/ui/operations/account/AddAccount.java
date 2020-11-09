package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.account.AddAccountViewer;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class AddAccount extends Operation {
    public AddAccount(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Viewer viewer = new AddAccountViewer(getPrompt(), getMenu());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            viewer.error();
            return;
        }

        int option = viewer.getInput();

        customer.openAccount(AccountType.values()[option]);
        viewer.success();
    }
}
