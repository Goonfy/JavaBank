package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class WithdrawMoneyPromptView extends PromptView {

    public WithdrawMoneyPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\nWithdrew money from account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError withdrawing money");
    }
}
