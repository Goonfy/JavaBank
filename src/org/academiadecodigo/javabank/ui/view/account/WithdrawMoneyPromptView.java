package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class WithdrawMoneyPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nWithdrew money from account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError withdrawing money");
    }

    public int getInput() {
        return createAccountMenu();
    }

    public int getAmount() {
        return createSelectionInput("Please enter the amount of money you want to withdraw: ");
    }
}
