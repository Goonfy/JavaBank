package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class ShowAccountPromptView extends PromptView {

    private String accounts;

    public ShowAccountPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\n" + accounts);
    }

    @Override
    public void error() {
        System.out.println("\nPlease create an account before proceeding...");
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }
}
