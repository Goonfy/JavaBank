package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends AccountController {

    private final TransferMoneyPromptView view;

    public TransferMoneyController(AccountService accountService, Customer customer) {
        super(accountService, customer);

        view = new TransferMoneyPromptView();
    }

    @Override
    public void execute() {
        if (getCustomer() == null) {
            view.error();
            return;
        }

        int accountId = view.createAccountMenu(getAccountService());

        int customerIdToTransferMoneyTo = view.createCustomerMenu(getAccountService());
        int accountIdToTransferMoneyTo = view.createAccountMenu(getAccountService().get(customerIdToTransferMoneyTo));
        int amountOfMoney = view.getAmount();

        getAccountService().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        view.success();
    }
}
