package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.customer.RemoveCustomerPromptView;

public class RemoveCustomerController extends CustomerController {

    private RemoveCustomerPromptView view;

    public RemoveCustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

    }

    @Override
    public void execute() {
        int customerId = view.createCustomerMenu(getCustomerService());
        if (customerId == -1) {
            view.error();
            return;
        }

        getCustomerService().remove(getCustomerService().get(customerId).getId());

        view.success();
    }

    public void setView(RemoveCustomerPromptView view) {
        this.view = view;
    }
}
