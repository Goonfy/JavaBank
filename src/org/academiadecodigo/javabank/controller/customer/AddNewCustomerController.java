package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CostumerController;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends CostumerController {

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
