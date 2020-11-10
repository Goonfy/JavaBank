package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.account.WithdrawMoneyView;

public class WithdrawMoneyController extends OperationController {
    public WithdrawMoneyController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        WithdrawMoneyView viewer = new WithdrawMoneyView(getPrompt(), getMenu(), getCustomerId(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        int accountId = viewer.getInput();

        Account account = customer.getAccountFromID(accountId);
        if (account == null || account.getAccountType() != AccountType.CHECKING) {
            viewer.error();
            return;
        }

        int amountToWithdraw = viewer.getAmount();
        account.debit(amountToWithdraw);

        viewer.success();
    }
}
