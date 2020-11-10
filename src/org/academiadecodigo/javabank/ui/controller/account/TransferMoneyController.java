package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.account.TransferMoneyView;

public class TransferMoneyController extends OperationController {
    public TransferMoneyController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        TransferMoneyView viewer = new TransferMoneyView(getPrompt(), getMenu(), getCustomerId(), getBank());

        int accountId = viewer.getInputAccount(getCustomerId());
        if (accountId == -1) {
            viewer.error();
            return;
        }

        int customerIdToTransferMoneyTo = viewer.getInput();
        int accountIdToTransferMoneyTo = viewer.getInputAccount(customerIdToTransferMoneyTo);
        int amountOfMoney = viewer.getAmount(customerIdToTransferMoneyTo);

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer == null) {
            viewer.error();
            return;
        }

        customer.getAccountManager().transfer(accountId, accountIdToTransferMoneyTo, amountOfMoney);

        viewer.success();
    }
}
