package org.academiadecodigo.javabank.view.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.PromptView;

import java.util.List;

public class ShowCustomerPromptView extends PromptView {

    private List<Customer> customers;

    public ShowCustomerPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\n" + customers.toString());
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
