package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.account.AddAccountView;

public class AddAccountController extends OperationController {
    public AddAccountController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        AddAccountView viewer = new AddAccountView(getPrompt(), getMenu());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            viewer.error();
            return;
        }

        int option = viewer.getInput();

        customer.openAccount(AccountType.values()[option]);
        viewer.success();
    }
}
