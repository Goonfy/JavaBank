package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.customer.AddNewCustomerView;

public class AddNewCustomerController extends OperationController {

    public AddNewCustomerController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        AddNewCustomerView viewer = new AddNewCustomerView(getPrompt(), getMenu());

        getBank().addCustomer(new Customer(viewer.getName(), viewer.getEmail(), viewer.getPhone()));

        viewer.success();
    }
}
