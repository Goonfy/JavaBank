package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.javabank.view.PromptView;

public class AddAccountPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nAdded new account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError adding new account");
    }
}
