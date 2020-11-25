package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.*;
import org.academiadecodigo.javabank.view.customer.AddNewCustomerPromptView;
import org.springframework.transaction.annotation.Transactional;

public class AddNewCustomerController extends CustomerController {

    private AddNewCustomerPromptView view;

    public AddNewCustomerController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);
    }

    @Override
    public void execute() {
        getCustomerService().add(view.getName(), view.getEmail(), view.getPhone());
    }

    public void setView(AddNewCustomerPromptView view) {
        this.view = view;
    }
}
