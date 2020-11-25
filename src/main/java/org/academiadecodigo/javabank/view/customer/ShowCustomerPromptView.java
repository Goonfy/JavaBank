package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class ShowCustomerPromptView extends PromptView {

    private String customers;

    public ShowCustomerPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\n" + customers);
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }
}
