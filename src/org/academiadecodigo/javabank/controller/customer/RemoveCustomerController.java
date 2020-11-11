package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CostumerController;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.view.customer.RemoveCustomerPromptView;

public class RemoveCustomerController extends CostumerController {

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
