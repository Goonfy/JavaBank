package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class CloseAccountView extends View {

    private final Bank bank;
    private final int customerId;

    public CloseAccountView(int customerId, Prompt prompt, Menu menu, Bank bank) {
        super(prompt, menu);

        this.bank = bank;
        this.customerId = customerId;
    }

    @Override
    public void success() {
        System.out.println("\nSuccessfully closed this account");
    }

    @Override
    public void error() {
        System.out.println("\nError closing account");
    }

    public int getInput() {
        return getMenu().createAccountMenu(bank.getCustomerFromID(customerId));
    }
}
