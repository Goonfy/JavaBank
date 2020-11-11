package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.javabank.view.PromptView;

public class CloseAccountPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nSuccessfully closed this account");
    }

    @Override
    public void error() {
        System.out.println("\nError closing account");
    }
}
