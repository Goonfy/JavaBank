package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.ShowAccountPromptView;

public class ShowAccountController extends AccountController {

    private final ShowAccountPromptView view;

    public ShowAccountController(AccountService accountService, Customer customer) {
        super(accountService, customer);

        view = new ShowAccountPromptView(getAccountService().getAllAccountsInfo());
    }

    @Override
    public void execute() {
        if (getCustomer() == null || getAccountService().getAllAccountsInfo().isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
