package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.javabank.view.PromptView;

public class AddNewCustomerPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nAdded new customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't add new customer");
    }
}
