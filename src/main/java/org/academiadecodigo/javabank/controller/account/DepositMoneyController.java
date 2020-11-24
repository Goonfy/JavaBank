package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends AccountController {

    private final DepositMoneyPromptView view;

    public DepositMoneyController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new DepositMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAccountService(), getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        int amountToDeposit = view.getAmount();

        getAccountService().deposit(accountId, amountToDeposit);

        view.success();
    }
}
