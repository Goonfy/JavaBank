package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.customer.AddNewCustomerPromptView;

public class AddNewCustomerController extends CustomerController {

    private final AddNewCustomerPromptView view;

    public AddNewCustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

        view = new AddNewCustomerPromptView();
    }

    @Override
    public void execute() {
        getCustomerService().add(view.getName(), view.getEmail(), view.getPhone());
    }
}
