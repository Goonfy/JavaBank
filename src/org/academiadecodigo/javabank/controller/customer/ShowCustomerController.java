package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.customer.ShowCustomerPromptView;

public class ShowCustomerController extends CustomerController {

    private final ShowCustomerPromptView view;

    public ShowCustomerController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

        view = new ShowCustomerPromptView(customerService.getAllCustomersInfo());
    }

    @Override
    public void execute() {
        if (getCustomerService().getNumberOfCustomers() <= 0) {
            view.error();
            return;
        }

        view.success();
    }
}
