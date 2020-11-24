package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.customer.ShowCustomerPromptView;

import java.util.List;

public class ShowCustomerController extends CustomerController {

    private ShowCustomerPromptView view;
    private List<Customer> customers;

    public ShowCustomerController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);
    }

    @Override
    public void execute() {
        customers = getCustomerService().listAll();
        view.setCustomers(customers);

        if (customers.isEmpty()) {
            view.error();
            return;
        }

        view.success();
    }

    public void setView(ShowCustomerPromptView view) {
        this.view = view;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
