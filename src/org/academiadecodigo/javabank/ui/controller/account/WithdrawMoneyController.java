package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends OperationController {

    private final Customer customer;

    public WithdrawMoneyController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        int accountId = getView().createAccountMenu(customer);

        int amountToWithdraw = getView().getAmount();
        customer.getAccountManager().withdraw(accountId, amountToWithdraw);

        getView().success();
    }
}
