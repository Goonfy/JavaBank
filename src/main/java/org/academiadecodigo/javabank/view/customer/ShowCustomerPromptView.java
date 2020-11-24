package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.PromptView;

import java.util.List;

public class ShowCustomerPromptView extends PromptView {

    private final List<Customer> customers;

    public ShowCustomerPromptView(Prompt prompt, List<Customer> customers) {
        super(prompt);
        this.customers = customers;
    }

    @Override
    public void success() {
        System.out.println("\n" + customers.toString());
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }
}
