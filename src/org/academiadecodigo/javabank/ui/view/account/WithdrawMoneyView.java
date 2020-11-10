package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class WithdrawMoneyView extends View {

    private final int customerId;
    private final Bank bank;

    public WithdrawMoneyView(Prompt prompt, Menu menu, int customerId, Bank bank) {
        super(prompt, menu);

        this.customerId = customerId;
        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("\nWithdrew money from account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError withdrawing money");
    }

    public int getInput() {
        return getMenu().createAccountMenu(bank.getCustomerFromID(customerId));
    }

    public int getAmount() {
        return getMenu().createSelectionInput("Please enter the amount of money you want to withdraw: ");
    }
}
