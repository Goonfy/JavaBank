package org.academiadecodigo.javabank.ui.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.ui.view.PromptView;

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
