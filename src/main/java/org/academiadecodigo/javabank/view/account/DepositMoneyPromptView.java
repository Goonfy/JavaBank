package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class DepositMoneyPromptView extends PromptView {

    public DepositMoneyPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\nDeposited money to account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError depositing money");
    }
}
