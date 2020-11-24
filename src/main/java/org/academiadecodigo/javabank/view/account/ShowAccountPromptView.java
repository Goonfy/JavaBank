package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class ShowAccountPromptView extends PromptView {

    private final String allAccountsInfo;

    public ShowAccountPromptView(Prompt prompt, String allAccountsInfo) {
        super(prompt);
        this.allAccountsInfo = allAccountsInfo;
    }

    @Override
    public void success() {
        System.out.println("\n" + allAccountsInfo);
    }

    @Override
    public void error() {
        System.out.println("\nPlease create an account before proceeding...");
    }
}
