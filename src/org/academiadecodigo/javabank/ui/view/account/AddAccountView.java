package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.view.View;

public class AddAccountView extends View {
    public AddAccountView(Prompt prompt, Menu menu) {
        super(prompt, menu);
    }

    @Override
    public void success() {
        System.out.println("\nAdded new account successfully");
    }

    @Override
    public void error() {
        System.out.println("\nError adding new account");
    }

    public int getInput() {
        return getMenu().createMenu(AccountType.values()) - 1;
    }
}
