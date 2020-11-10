package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class TransferMoneyPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nSuccessfully sent money");
    }

    @Override
    public void error() {
        System.out.println("\nError transferring money");
    }

    public int getInput() {
        return createCustomerMenu();
    }

    public int getInputAccount(int customerId) {
        return createAccountMenu();
    }

    public int getAmount(int customerId) {
        IntegerInputScanner inputScanner = new IntegerRangeInputScanner(1, (int) bank.getCustomerFromID(customerId).getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + bank.getCustomerFromID(customerId).getName() + ": ");
        return getPrompt().getUserInput(inputScanner);
    }
}
