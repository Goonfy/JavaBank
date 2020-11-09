package org.academiadecodigo.javabank.ui.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;

public abstract class Operation {

    private final Prompt prompt;
    private final Bank bank;
    private final int customerId;
    private final Menu menu;

    public Operation(int customerId, Prompt prompt, Bank bank, Menu menu) {
        this.customerId = customerId;
        this.prompt = prompt;
        this.bank = bank;
        this.menu = menu;
    }

    public abstract void execute();

    public int getCustomerId() {
        return customerId;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public Bank getBank() {
        return bank;
    }

    public Menu getMenu() {
        return menu;
    }
}
