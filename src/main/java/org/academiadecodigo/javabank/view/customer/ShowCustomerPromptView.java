package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class ShowCustomerPromptView extends PromptView {

    private final String allCustomersInfo;

    public ShowCustomerPromptView(Prompt prompt, String allCustomersInfo) {
        super(prompt);
        this.allCustomersInfo = allCustomersInfo;
    }

    @Override
    public void success() {
        System.out.println("\n" + allCustomersInfo);
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }
}
