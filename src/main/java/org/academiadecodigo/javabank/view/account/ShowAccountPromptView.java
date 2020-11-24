package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.view.PromptView;

import java.util.List;

public class ShowAccountPromptView extends PromptView {

    private final List<Account> accounts;

    public ShowAccountPromptView(Prompt prompt, List<Account> accounts) {
        super(prompt);
        this.accounts = accounts;
    }

    @Override
    public void success() {
        System.out.println("\n" + accounts.toString());
    }

    @Override
    public void error() {
        System.out.println("\nPlease create an account before proceeding...");
    }
}
