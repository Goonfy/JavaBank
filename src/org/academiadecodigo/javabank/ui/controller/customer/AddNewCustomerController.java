package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends OperationController {

    public AddNewCustomerController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        getBank().addCustomer(new Customer(getView().getName(), getView().getEmail(), getView().getPhone()));
    }
}
