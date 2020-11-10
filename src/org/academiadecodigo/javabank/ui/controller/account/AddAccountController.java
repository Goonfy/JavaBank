package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class AddAccountController extends OperationController {
    public AddAccountController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        int selectedCustomer = getView().createCustomerMenu();
        Customer customer = getBank().getCustomerFromID(selectedCustomer);
        if (customer == null) {
            return;
        }

        customer.openAccount(AccountType.values()[getView().createMenu(AccountType.values())]);
    }
}
