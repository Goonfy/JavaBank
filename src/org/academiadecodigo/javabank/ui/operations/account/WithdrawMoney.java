package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.Viewer;
import org.academiadecodigo.javabank.ui.viewer.account.WithdrawMoneyViewer;

public class WithdrawMoney extends Operation {
    public WithdrawMoney(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        WithdrawMoneyViewer viewer = new WithdrawMoneyViewer(getPrompt(), getMenu(), getCustomerId(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        int accountId = viewer.getInput();
        if (accountId == -1) {
            viewer.error();
            return;
        }

        int amountToWithdraw = viewer.getAmount();

        Account account = customer.getAccountFromID(accountId);
        account.debit(amountToWithdraw);

        viewer.success();
    }
}
