package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends AccountController {

    private TransferMoneyPromptView view;
    private final JpaCustomerService customerService;

    public TransferMoneyController(JpaAccountService accountService, JpaAuthenticationService authenticationService, JpaCustomerService customerService) {
        super(accountService, authenticationService);

        this.customerService = customerService;
    }

    @Override
    public void execute() {
        int accountId = view.createAccountMenu(getAccountService(), getAuthenticationService().getAccessingCustomer());
        if (accountId == -1) {
            view.error();
            return;
        }

        int customerIdToTransferMoneyTo = view.createCustomerMenu(customerService);
        int accountIdToTransferMoneyTo = view.createAccountMenu(getAccountService(), customerService.get(customerIdToTransferMoneyTo));
        int amountOfMoney = view.getAmount();

        getAccountService().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        view.success();
    }

    public void setView(TransferMoneyPromptView view) {
        this.view = view;
    }
}
