package org.academiadecodigo.javabank.ui.controller;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.View;

public abstract class OperationController implements Controller {

    private final Bank bank;

    public OperationController(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
