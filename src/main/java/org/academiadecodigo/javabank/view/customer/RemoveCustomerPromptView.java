package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class RemoveCustomerPromptView extends PromptView {

    public RemoveCustomerPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\nRemoved customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't remove customer");
    }
}
