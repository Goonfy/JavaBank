package org.academiadecodigo.javabank.ui.viewer.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class ShowCustomerViewer extends Viewer {

    private final Bank bank;

    public ShowCustomerViewer(Prompt prompt, Menu menu, Bank bank) {
        super(prompt, menu);

        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("\n" + bank.getAllCustomersInfo());
    }

    @Override
    public void error() {
        System.out.println("\nNo customers found, please create one first");
    }
}
