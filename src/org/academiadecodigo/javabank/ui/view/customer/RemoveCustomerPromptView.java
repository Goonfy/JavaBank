package org.academiadecodigo.javabank.ui.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class RemoveCustomerPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nRemoved customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't remove customer");
    }

    public int getInput() {
        return createCustomerMenu();
    }
}
