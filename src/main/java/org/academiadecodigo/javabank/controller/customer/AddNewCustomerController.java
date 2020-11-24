package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends CustomerController {

    private AddNewCustomerPromptView view;

    public AddNewCustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
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
