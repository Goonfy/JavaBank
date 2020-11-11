package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends CustomerController {

    private final AddNewCustomerPromptView view;

    public AddNewCustomerController(CustomerService customerService) {
        super(customerService);

        view = new AddNewCustomerPromptView();
    }

    @Override
    public void execute() {
        getCustomerService().add(new Customer(view.getName(), view.getEmail(), view.getPhone()));
    }
}
