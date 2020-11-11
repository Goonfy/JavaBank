package org.academiadecodigo.javabank.controller.customer;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.customer.RemoveCustomerPromptView;

public class RemoveCustomerController extends CustomerController {

    private final RemoveCustomerPromptView view;

    public RemoveCustomerController(CustomerService customerService) {
        super(customerService);

        view = new RemoveCustomerPromptView();
    }

    @Override
    public void execute() {
        int customerId = view.createCustomerMenu(getCustomerService());
        if (customerId == -1) {
            view.error();
            return;
        }

        getCustomerService().removeCustomer(getCustomerService().get(customerId));

        view.success();
    }
}
