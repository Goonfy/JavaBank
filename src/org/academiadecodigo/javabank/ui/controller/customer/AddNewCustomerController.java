package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends OperationController {

    private final AddNewCustomerPromptView view;

    public AddNewCustomerController(Bank bank) {
        super(bank);

        view = new AddNewCustomerPromptView();
    }

    @Override
    public void execute() {
        getBank().addCustomer(new Customer(view.getName(), view.getEmail(), view.getPhone()));
    }
}
