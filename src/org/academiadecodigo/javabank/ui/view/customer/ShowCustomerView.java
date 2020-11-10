package org.academiadecodigo.javabank.ui.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class ShowCustomerView extends View {

    private final Bank bank;

    public ShowCustomerView(Prompt prompt, Menu menu, Bank bank) {
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
