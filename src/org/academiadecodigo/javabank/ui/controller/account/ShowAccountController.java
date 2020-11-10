package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.View;
import org.academiadecodigo.javabank.ui.view.account.ShowAccountView;

public class ShowAccountController extends OperationController {
    public ShowAccountController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        View view = new ShowAccountView(getCustomerId(), getPrompt(), getMenu(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer.getAllAccountsInfo().isEmpty()) {
            view.error();
        }

        view.success();
    }
}
