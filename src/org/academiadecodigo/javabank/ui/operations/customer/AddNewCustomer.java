package org.academiadecodigo.javabank.ui.operations.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.customer.AddNewCustomerViewer;

public class AddNewCustomer extends Operation {

    public AddNewCustomer(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        AddNewCustomerViewer viewer = new AddNewCustomerViewer(getPrompt(), getMenu());

        getBank().addCustomer(new Customer(viewer.getName(), viewer.getEmail(), viewer.getPhone()));

        viewer.success();
    }
}
