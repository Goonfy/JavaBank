package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.model.account.*;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private final AddAccountPromptView view;

    public AddAccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new AddAccountPromptView();
    }

    @Override
    public void execute() {
        if (getAccountService() == null) {
            view.error();
            return;
        }

        AbstractAccount account = null;
        AccountType accountType = AccountType.values()[view.createMenu(AccountType.values()) - 1];

        switch (accountType) {

            case CHECKING:
                account = new CheckingAccount();
                break;
            case SAVINGS:
                account = new SavingsAccount();
                break;
        }

        getAccountService().add(account);

        view.success();
    }
}
