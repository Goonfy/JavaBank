package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends AccountController {

    private final DepositMoneyPromptView view;

    public DepositMoneyController(AccountService accountService, Customer customer) {
        super(accountService, customer);

        view = new DepositMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        int amountToDeposit = view.getAmount();

        getAccountService().deposit(accountId, amountToDeposit);

        view.success();
    }
}
