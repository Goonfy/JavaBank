package org.academiadecodigo.javabank.ui.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class ShowCustomerPromptView extends PromptView {

    private String allCustomersInfo;

    public ShowCustomerPromptView(String allCustomersInfo) {
        this.allCustomersInfo = allCustomersInfo;
    }

    @Override
    public void success() {
        System.err.println("\n" + allCustomersInfo);
    }

    @Override
    public void error() {
        System.err.println("\nPlease create a costumer before proceeding...");
    }
}
