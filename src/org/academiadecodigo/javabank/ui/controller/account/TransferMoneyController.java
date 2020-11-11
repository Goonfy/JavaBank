package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends OperationController {

    private final Customer customer;
    private final TransferMoneyPromptView view;

    public TransferMoneyController(Bank bank, Customer customer) {
        super(bank);

        this.customer = customer;
        view = new TransferMoneyPromptView();
    }

    @Override
    public void execute() {
        if (customer == null) {
            view.error();
            return;
        }

        int accountId = view.createAccountMenu(customer);

        int customerIdToTransferMoneyTo = view.createCustomerMenu(getBank());
        int accountIdToTransferMoneyTo = view.createAccountMenu(getBank().getCustomerFromID(customerIdToTransferMoneyTo));
        int amountOfMoney = view.getAmount();

        customer.getAccountManager().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        view.success();
    }
}
