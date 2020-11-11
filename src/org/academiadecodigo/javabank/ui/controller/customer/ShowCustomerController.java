package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.ShowAccountPromptView;
import org.academiadecodigo.javabank.ui.view.customer.ShowCustomerPromptView;

public class ShowCustomerController extends OperationController {

    private final ShowCustomerPromptView view;

    public ShowCustomerController(Bank bank) {
        super(bank);

        view = new ShowCustomerPromptView(bank.getAllCustomersInfo());
    }

    @Override
    public void execute() {
        if (getBank().getNumberOfCustomers() <= 0) {
            view.error();
            return;
        }

        view.success();
    }
}
