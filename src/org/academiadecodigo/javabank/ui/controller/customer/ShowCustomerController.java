package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.View;
import org.academiadecodigo.javabank.ui.view.customer.ShowCustomerView;

public class ShowCustomerController extends OperationController {

    public ShowCustomerController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        View view = new ShowCustomerView(getPrompt(), getMenu(), getBank());
        if (getBank().getNumberOfCustomers() <= 0) {
            view.error();
            return;
        }

        view.success();
    }
}
