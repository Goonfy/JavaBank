package org.academiadecodigo.javabank.ui.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;

public abstract class OperationController implements Controller {

    private final Prompt prompt;
    private final Bank bank;
    private final int customerId;
    private final Menu menu;

    //TODO leave only BANK
    public OperationController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        this.customerId = customerId;
        this.prompt = prompt;
        this.bank = bank;
        this.menu = menu;
    }

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
