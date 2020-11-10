package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.customer.RemoveCustomerPromptView;

public class RemoveCustomerController extends OperationController {

    public RemoveCustomerController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        int customerId = getView().createCustomerMenu(getBank());
        if (customerId == -1) {
            getView().error();
            return;
        }

        getBank().removeCustomer(getBank().getCustomerFromID(customerId));

        getView().success();
    }
}
