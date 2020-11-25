package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.model.account.SavingsAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private AddAccountPromptView view;

    public AddAccountController(AccountService accountService, AuthenticationService authenticationService) {
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
