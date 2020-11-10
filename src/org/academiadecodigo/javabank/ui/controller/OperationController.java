package org.academiadecodigo.javabank.ui.controller;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.View;

public abstract class OperationController implements Controller {

    private final Bank bank;
    private final PromptView view;

    public OperationController(Bank bank, PromptView view) {
        this.bank = bank;
        this.view = view;
    }

    public Bank getBank() {
        return bank;
    }

    public PromptView getView() {
        return view;
    }
}
