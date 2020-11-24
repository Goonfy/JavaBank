package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.CloseAccountPromptView;

public class CloseAccountController extends AccountController {

    private CloseAccountPromptView view;

    public CloseAccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAccountService(), getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        getAccountService().remove(accountId);
        view.success();
    }

    public void setView(CloseAccountPromptView view) {
        this.view = view;
    }
}
