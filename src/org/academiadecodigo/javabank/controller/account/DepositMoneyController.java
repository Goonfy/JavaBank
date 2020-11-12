package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends AccountController {

    private final DepositMoneyPromptView view;

    public DepositMoneyController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new DepositMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        int amountToDeposit = view.getAmount();

        getAccountService().deposit(getAuthenticationService().getAccessingCustomer(), accountId, amountToDeposit);

        view.success();
    }
}
