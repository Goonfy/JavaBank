package org.academiadecodigo.javabank.ui.operations.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.customer.RemoveCustomerViewer;

public class RemoveCustomer extends Operation {

    public RemoveCustomer(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        RemoveCustomerViewer viewer = new RemoveCustomerViewer(getPrompt(), getMenu());

        int customerId = viewer.getInput();
        if (customerId == -1) {
            viewer.error();
            return;
        }

        getBank().removeCustomer(getBank().getCustomerFromID(customerId));

        viewer.success();
    }
}
