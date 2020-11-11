package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.CloseAccountPromptView;

public class CloseAccountController extends OperationController {

    private final Customer customer;
    private final CloseAccountPromptView view;

    public CloseAccountController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new CloseAccountPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(customer);
        if (accountId == -1) {
            view.error();
            return;
        }

        customer.closeAccount(customer.getAccountManager().getAccountFromID(accountId));
        view.success();
    }
}
