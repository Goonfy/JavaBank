package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class TransferMoneyPromptView extends PromptView {

    public TransferMoneyPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\nSuccessfully sent money");
    }

    @Override
    public void error() {
        System.out.println("\nError transferring money");
    }
}
