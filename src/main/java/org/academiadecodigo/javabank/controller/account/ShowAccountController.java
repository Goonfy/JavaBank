package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.account.AbstractAccount;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.PromptView;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

import java.util.List;

public class ShowAccountController extends AccountController {

    public ShowAccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);
    }

    @Override
    public void execute() {
        List<AbstractAccount> accounts = getAccountService().getAllAccountsInfoFrom(getAuthenticationService().getAccessingCustomer());
        PromptView view = new ShowAccountPromptView(accounts.toString());

        if (accounts.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
