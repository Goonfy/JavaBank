package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

import java.util.List;

public class ShowAccountController extends AccountController {

    private ShowAccountPromptView view;

    public ShowAccountController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);
    }

    @Override
    public void execute() {
        List<AbstractAccount> accounts = getAccountService().getAllAccountsInfoFrom(getAuthenticationService().getAccessingCustomer());
        view.setAccounts(accounts.toString());

        if (accounts.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }

    public void setView(ShowAccountPromptView view) {
        this.view = view;
    }
}
