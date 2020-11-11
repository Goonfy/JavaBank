package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends AccountController {

    private final WithdrawMoneyPromptView view;

    public WithdrawMoneyController(AccountService customerServiceImplementation, Customer customer) {
        super(customerServiceImplementation, customer);

        view = new WithdrawMoneyPromptView();
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAccountService());

        int amountToWithdraw = view.getAmount();
        getAccountService().withdraw(accountId, amountToWithdraw);

        view.success();
    }
}
