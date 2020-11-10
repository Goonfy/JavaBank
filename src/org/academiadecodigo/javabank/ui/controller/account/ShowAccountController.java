package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.ShowAccountPromptView;

public class ShowAccountController extends OperationController {

    private final Customer customer;

    public ShowAccountController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        if (customer == null || customer.getAccountManager().getAllAccountsInfo().isEmpty()) {
            getView().error();
            return;
        }

        getView().success();
    }
}
