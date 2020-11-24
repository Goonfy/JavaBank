package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.customer.ShowCustomerPromptView;

import java.util.List;

public class ShowCustomerController extends CustomerController {

    public ShowCustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);
    }

    @Override
    public void execute() {
        List<Customer> customers = getCustomerService().listAll();
        ShowCustomerPromptView view = new ShowCustomerPromptView(customers.toString());

        if (customers.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }
}
