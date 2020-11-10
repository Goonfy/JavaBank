package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.DepositMoneyPromptView;

public class DepositMoneyController extends OperationController {
    public DepositMoneyController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        DepositMoneyPromptView viewer = new DepositMoneyPromptView(getPrompt(), getMenu(), getCustomerId(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            viewer.error();
            return;
        }

        int accountId = viewer.getInput();
        if (accountId == -1) {
            viewer.error();
            return;
        }

        int amountToDeposit = viewer.getAmount();

        Account account = customer.getAccountFromID(accountId);
        account.credit(amountToDeposit);

        viewer.success();
    }
}
