package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class ShowAccountViewer extends Viewer {

    private final Bank bank;
    private final int customerId;

    public ShowAccountViewer(int customerId, Prompt prompt, Menu menu, Bank bank) {
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
