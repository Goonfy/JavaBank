package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.*;
import org.academiadecodigo.javabank.view.customer.ShowCustomerPromptView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ShowCustomerController extends CustomerController {

    private ShowCustomerPromptView view;

    public ShowCustomerController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);
    }

    @Override
    public void execute() {
        List<Customer> customers = getCustomerService().listAll();
        view.setCustomers(customers.toString());

        if (customers.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }

    public void setView(ShowCustomerPromptView view) {
        this.view = view;
    }
}
