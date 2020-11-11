package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.CloseAccountPromptView;

public class CloseAccountController extends AccountController {

    private final CloseAccountPromptView view;

    public CloseAccountController(AccountService accountService, Customer customer) {
        super(accountService, customer);

        view = new CloseAccountPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        getAccountService().close(getAccountService().get(accountId));
        view.success();
    }
}
