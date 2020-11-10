package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.account.DepositMoneyView;

public class DepositMoneyController extends OperationController {
    public DepositMoneyController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        DepositMoneyView viewer = new DepositMoneyView(getPrompt(), getMenu(), getCustomerId(), getBank());

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
