package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.viewer.Viewer;
import org.academiadecodigo.javabank.ui.viewer.account.TransferMoneyViewer;

public class TransferMoney extends Operation {
    public TransferMoney(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        TransferMoneyViewer viewer = new TransferMoneyViewer(getPrompt(), getMenu(), getCustomerId(), getBank());

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
