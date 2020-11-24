package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.model.account.*;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private AddAccountPromptView view;

    public AddAccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(accountService, authenticationService);
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

    public void setView(AddAccountPromptView view) {
        this.view = view;
    }
}
