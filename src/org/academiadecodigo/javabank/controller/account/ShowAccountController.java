package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

public class ShowAccountController extends AccountController {

    private final ShowAccountPromptView view;

    public ShowAccountController(Bank bank, Customer customer) {
        super(bank, customer);

        view = new ShowAccountPromptView(customer.getAccountManager().getAllAccountsInfo());
    }

    @Override
    public void execute() {
        if (getCustomer() == null || getCustomer().getAccountManager().getAllAccountsInfo().isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
