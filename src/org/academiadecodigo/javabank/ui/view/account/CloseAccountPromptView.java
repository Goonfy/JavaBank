package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.view.PromptView;

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
