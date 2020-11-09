package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class WithdrawMoneyViewer extends Viewer {

    private final int customerId;
    private final Bank bank;

    public WithdrawMoneyViewer(Prompt prompt, Menu menu, int customerId, Bank bank) {
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
