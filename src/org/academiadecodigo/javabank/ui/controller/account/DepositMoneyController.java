package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends OperationController {

    private final Customer customer;

    public DepositMoneyController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        int accountId = getView().createAccountMenu(customer);
        if (accountId == -1) {
            getView().error();
            return;
        }

        int amountToDeposit = getView().getAmount();

        Account account = customer.getAccountManager().getAccountFromID(accountId);
        account.credit(amountToDeposit);

        getView().success();
    }
}
