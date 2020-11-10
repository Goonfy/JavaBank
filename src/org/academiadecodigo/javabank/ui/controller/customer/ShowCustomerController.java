package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class ShowCustomerController extends OperationController {

    public ShowCustomerController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        if (getBank().getNumberOfCustomers() <= 0) {
            return;
        }

    }
}
