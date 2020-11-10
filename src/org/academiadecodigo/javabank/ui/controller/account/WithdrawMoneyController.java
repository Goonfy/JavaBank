package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.WithdrawMoneyPromptView;

public class WithdrawMoneyController extends OperationController {
    public WithdrawMoneyController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        WithdrawMoneyPromptView viewer = new WithdrawMoneyPromptView(getPrompt(), getMenu(), getCustomerId(), getBank());

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
