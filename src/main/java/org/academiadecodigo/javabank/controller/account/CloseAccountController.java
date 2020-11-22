package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.view.account.CloseAccountPromptView;

public class CloseAccountController extends AccountController {

    private final CloseAccountPromptView view;

    public CloseAccountController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new CloseAccountPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        getAccountService().remove(accountId);
        view.success();
    }
}
