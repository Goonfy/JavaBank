package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.javabank.view.PromptView;

public class WithdrawMoneyPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nWithdrew money from account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError withdrawing money");
    }
}
