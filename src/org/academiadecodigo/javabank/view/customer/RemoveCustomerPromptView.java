package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.javabank.view.PromptView;

public class RemoveCustomerPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nRemoved customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't remove customer");
    }
}
