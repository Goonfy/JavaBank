package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends AccountController {

    private final WithdrawMoneyPromptView view;

    public WithdrawMoneyController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new WithdrawMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAuthenticationService().getAccessingCustomer());

        int amountToWithdraw = view.getAmount();
        getAccountService().withdraw(getAuthenticationService().getAccessingCustomer(), accountId, amountToWithdraw);

        view.success();
    }
}
