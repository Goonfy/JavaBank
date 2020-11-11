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
    private final WithdrawMoneyPromptView view;

    public WithdrawMoneyController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new WithdrawMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(customer);

        int amountToWithdraw = view.getAmount();
        customer.getAccountManager().withdraw(accountId, amountToWithdraw);

        view.success();
    }
}
