package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class TransferMoneyViewer extends Viewer {

    private final Bank bank;
    private final int costumerId;

    public TransferMoneyViewer(Prompt prompt, Menu menu, int costumerId, Bank bank) {
        super(prompt, menu);

        this.costumerId = costumerId;
        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("Successfully sent money to " + bank.getCustomerFromID(accountToSendMoneyTo).getName());
    }

    @Override
    public void error() {
        System.out.println("\nError transferring money\n");
    }

    @Override
    public int getInput() {
        int accountId = getMenu().createAccountMenu(bank.getCustomerFromID(costumerId));
        if (accountId == -1) {
            return -1;
        }

        int customerIdToTransferMoneyTo = getMenu().createCustomerMenu();
        int accountIdToTransferMoneyTo = getMenu().createAccountMenu(bank.getCustomerFromID(customerIdToTransferMoneyTo));

        IntegerInputScanner inputScanner = new IntegerRangeInputScanner(1, (int) bank.getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + bank.getCustomerFromID(customerIdToTransferMoneyTo).getName() + ": ");
        int amountOfMoney = getPrompt().getUserInput(inputScanner);

        return 0;
    }
}
