package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.customer.RemoveCustomerPromptView;

public class RemoveCustomerController extends OperationController {

    private final RemoveCustomerPromptView view;

    public RemoveCustomerController(Bank bank) {
        super(bank);

        view = new RemoveCustomerPromptView();
    }

    @Override
    public void execute() {
        int customerId = view.createCustomerMenu(getBank());
        if (customerId == -1) {
            view.error();
            return;
        }

        getBank().removeCustomer(getBank().getCustomerFromID(customerId));

        view.success();
    }
}
