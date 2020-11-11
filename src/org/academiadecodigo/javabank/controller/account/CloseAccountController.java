package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.CloseAccountPromptView;

public class CloseAccountController extends AccountController {

    private final CloseAccountPromptView view;

    public CloseAccountController(Bank bank, Customer customer) {
        super(bank, customer);

        view = new CloseAccountPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        getCustomer().closeAccount(getCustomer().getAccountManager().getAccountFromID(accountId));
        view.success();
    }
}
