package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends AccountController {

    private final TransferMoneyPromptView view;
    private final CustomerService customerService;

    public TransferMoneyController(AccountService accountService, AuthenticationService authenticationService, CustomerService customerService) {
        super(accountService, authenticationService);

        view = new TransferMoneyPromptView();
        this.customerService = customerService;
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        int customerIdToTransferMoneyTo = view.createCustomerMenu(customerService);
        int accountIdToTransferMoneyTo = view.createAccountMenu(customerService.get(customerIdToTransferMoneyTo));
        int amountOfMoney = view.getAmount();

        getAccountService().transfer(getAuthenticationService().getAccessingCustomer(), accountId, accountIdToTransferMoneyTo, amountOfMoney);

        view.success();
    }
}
