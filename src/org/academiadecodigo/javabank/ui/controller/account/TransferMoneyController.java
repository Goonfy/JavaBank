package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.TransferMoneyPromptView;

public class TransferMoneyController extends OperationController {

    private final Customer customer;

    public TransferMoneyController(Bank bank, PromptView view, Customer customer) {
        super(bank, view);

        this.customer = customer;
    }

    @Override
    public void execute() {
        if (customer == null) {
            getView().error();
            return;
        }

        int accountId = getView().createAccountMenu(customer);

        int customerIdToTransferMoneyTo = getView().createCustomerMenu(getBank());
        int accountIdToTransferMoneyTo = getView().createAccountMenu(getBank().getCustomerFromID(customerIdToTransferMoneyTo));
        int amountOfMoney = getView().getAmount();

        customer.getAccountManager().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        getView().success();
    }
}
