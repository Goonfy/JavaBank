package org.academiadecodigo.javabank.ui.operations.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.Viewer;
import org.academiadecodigo.javabank.ui.viewer.customer.ShowCustomerViewer;

public class ShowCustomer extends Operation {

    public ShowCustomer(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Viewer viewer = new ShowCustomerViewer(getPrompt(), getMenu(), getBank());
        if (getBank().getNumberOfCustomers() <= 0) {
            viewer.error();
            return;
        }

        viewer.success();
    }
}
