package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends AccountController {

    private WithdrawMoneyPromptView view;

    public WithdrawMoneyController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAccountService(), getAuthenticationService().getAccessingCustomer());

        int amountToWithdraw = view.getAmount();
        getAccountService().withdraw(accountId, amountToWithdraw);

        view.success();
    }

    public void setView(WithdrawMoneyPromptView view) {
        this.view = view;
    }
}
