package org.academiadecodigo.javabank.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.view.PromptView;

import java.util.List;

public class ShowAccountPromptView extends PromptView {

    private List<AbstractAccount> accounts;

    public ShowAccountPromptView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\n" + accounts.toString());
    }

    @Override
    public void error() {
        System.out.println("\nPlease create an account before proceeding...");
    }

    public void setAccounts(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }
}
