package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.view.account.AddAccountPromptView;

public class AddAccountController extends AccountController {

    private final AddAccountPromptView view;

    public AddAccountController(AccountService accountService, AuthenticationService authenticationService) {
        super(accountService, authenticationService);

        view = new AddAccountPromptView();
    }

    @Override
    public void execute() {
        if (getAccountService() == null) {
            view.error();
            return;
        }

        Account account = null;
        AccountType accountType = AccountType.values()[view.createMenu(AccountType.values()) - 1];

        switch (accountType) {

            case CHECKING:
                account = new CheckingAccount(accountType);
                break;
            case SAVINGS:
                account = new SavingsAccount(accountType);
                break;
        }

        getAuthenticationService().getAccessingCustomer().addAccount(account.getId());
        getAccountService().add(account);

        view.success();
    }
}
