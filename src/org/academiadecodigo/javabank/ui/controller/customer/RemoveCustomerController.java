package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.customer.RemoveCustomerView;

public class RemoveCustomerController extends OperationController {

    public RemoveCustomerController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        RemoveCustomerView viewer = new RemoveCustomerView(getPrompt(), getMenu());

        int customerId = viewer.getInput();
        if (customerId == -1) {
            viewer.error();
            return;
        }

        getBank().removeCustomer(getBank().getCustomerFromID(customerId));

        viewer.success();
    }
}
