package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class TransferMoneyView extends View {

    private final Bank bank;
    private final int customerId;

    public TransferMoneyView(Prompt prompt, Menu menu, int costumerId, Bank bank) {
        super(prompt, menu);

        this.customerId = costumerId;
        this.bank = bank;
    }

    @Override
    public void success() {
        System.out.println("\nSuccessfully sent money");
    }

    @Override
    public void error() {
        System.out.println("\nError transferring money");
    }

    public int getInput() {
        return getMenu().createCustomerMenu();
    }

    public int getInputAccount(int customerId) {
        return getMenu().createAccountMenu(bank.getCustomerFromID(customerId));
    }

    public int getAmount(int customerId) {
        IntegerInputScanner inputScanner = new IntegerRangeInputScanner(1, (int) bank.getCustomerFromID(customerId).getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + bank.getCustomerFromID(customerId).getName() + ": ");
        return getPrompt().getUserInput(inputScanner);
    }
}
