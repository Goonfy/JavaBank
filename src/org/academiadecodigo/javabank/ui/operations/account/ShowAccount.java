package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.Viewer;
import org.academiadecodigo.javabank.ui.viewer.account.ShowAccountViewer;

public class ShowAccount extends Operation {
    public ShowAccount(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Viewer viewer = new ShowAccountViewer(getCustomerId(), getPrompt(), getMenu(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer.getAllAccountsInfo().isEmpty()) {
            viewer.error();
        }

        viewer.success();
    }
}
