package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CostumerController;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.view.customer.ShowCustomerPromptView;

public class ShowCustomerController extends CostumerController {

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
