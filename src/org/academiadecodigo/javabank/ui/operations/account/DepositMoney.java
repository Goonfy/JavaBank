package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.account.DepositMoneyViewer;

public class DepositMoney extends Operation {
    public DepositMoney(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        DepositMoneyViewer viewer = new DepositMoneyViewer(getPrompt(), getMenu(), getCustomerId(), getBank());

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
