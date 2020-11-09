package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class DepositMoneyViewer extends Viewer {

    private final int customerId;
    private final Bank bank;

    public DepositMoneyViewer(Prompt prompt, Menu menu, int customerId, Bank bank) {
        super(prompt, menu);

        this.customerId = customerId;
        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("\nDeposited money to account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError depositing money");
    }

    public int getInput() {
        return getMenu().createAccountMenu(bank.getCustomerFromID(customerId));
    }

    public int getAmount() {
        return getMenu().createSelectionInput("Please enter the amount of money you want to deposit: ");
    }
}
