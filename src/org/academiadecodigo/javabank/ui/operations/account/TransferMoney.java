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
        Viewer viewer = new TransferMoneyViewer(getPrompt(), getMenu(), getCustomerId(), getBank());

        Customer customer = getBank().getCustomerFromID(customerNumber);
        if (customer == null) {

            return;
        }

        customer.getAccountManager().transfer(accountNumber, accountToSendMoneyTo, amountToTransfer);
    }
}
