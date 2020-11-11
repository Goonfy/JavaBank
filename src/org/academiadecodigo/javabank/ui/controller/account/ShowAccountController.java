package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.ShowAccountPromptView;

public class ShowAccountController extends OperationController {

    private final Customer customer;
    private final ShowAccountPromptView view;

    public ShowAccountController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new ShowAccountPromptView(customer.getAccountManager().getAllAccountsInfo());
    }

    @Override
    public void execute() {
        if (customer == null || customer.getAccountManager().getAllAccountsInfo().isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
