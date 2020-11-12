package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

public class ShowAccountController extends AccountController {

    private final ShowAccountPromptView view;

    public ShowAccountController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new ShowAccountPromptView(authenticationService.getAccessingCustomer().getAllAccountsInfo());
    }

    @Override
    public void execute() {
        if (getAuthenticationService().getAccessingCustomer().getAllAccountsInfo().isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
