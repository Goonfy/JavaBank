package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends OperationController {

    private final Customer customer;
    private final DepositMoneyPromptView view;

    public DepositMoneyController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new DepositMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(customer);
        if (accountId == -1) {
            view.error();
            return;
        }

        int amountToDeposit = view.getAmount();

        Account account = customer.getAccountManager().getAccountFromID(accountId);
        account.credit(amountToDeposit);

        view.success();
    }
}
