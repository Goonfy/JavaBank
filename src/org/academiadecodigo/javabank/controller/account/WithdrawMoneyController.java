package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends AccountController {

    private final WithdrawMoneyPromptView view;

    public WithdrawMoneyController(Bank bank, Customer customer) {
        super(bank, customer);

        view = new WithdrawMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getCustomer());

        int amountToWithdraw = view.getAmount();
        getCustomer().getAccountManager().withdraw(accountId, amountToWithdraw);

        view.success();
    }
}
