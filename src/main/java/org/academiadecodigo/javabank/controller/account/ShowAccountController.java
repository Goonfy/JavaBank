package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.PromptView;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

import java.util.List;

public class ShowAccountController extends AccountController {

    private ShowAccountPromptView view;
    private List<AbstractAccount> accounts;

    public ShowAccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);
    }

    @Override
    public void execute() {
        accounts = getAccountService().getAllAccountsInfoFrom(getAuthenticationService().getAccessingCustomer());
        view.setAccounts(accounts);

        if (accounts.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }

    public void setView(ShowAccountPromptView view) {
        this.view = view;
    }

    public void setAccounts(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }
}
