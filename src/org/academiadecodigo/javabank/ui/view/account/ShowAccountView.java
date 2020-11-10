package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class ShowAccountView extends View {

    private final Bank bank;
    private final int customerId;

    public ShowAccountView(int customerId, Prompt prompt, Menu menu, Bank bank) {
        super(prompt, menu);

        this.customerId = customerId;
        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("\n" + bank.getCustomerFromID(customerId).getAllAccountsInfo());
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }
}
