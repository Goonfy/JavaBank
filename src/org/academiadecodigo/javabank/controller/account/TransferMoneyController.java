package org.academiadecodigo.javabank.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends AccountController {

    private final TransferMoneyPromptView view;

    public TransferMoneyController(Bank bank, Customer customer) {
        super(bank, customer);

        view = new TransferMoneyPromptView();
    }

    @Override
    public void execute() {
        if (getCustomer() == null) {
            view.error();
            return;
        }

        int accountId = view.createAccountMenu(getCustomer());

        int customerIdToTransferMoneyTo = view.createCustomerMenu(getBank());
        int accountIdToTransferMoneyTo = view.createAccountMenu(getBank().getCustomerFromID(customerIdToTransferMoneyTo));
        int amountOfMoney = view.getAmount();

        getCustomer().getAccountManager().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        view.success();
    }
}
