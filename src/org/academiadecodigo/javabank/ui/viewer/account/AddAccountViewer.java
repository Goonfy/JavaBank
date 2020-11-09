package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class AddAccountViewer extends Viewer {
    public AddAccountViewer(Prompt prompt, Menu menu) {
        super(prompt, menu);
    }

    @Override
    public void success() {
        System.out.println("\nAdded new account successfully\n");
    }

    @Override
    public void error() {
        System.out.println("\nError adding new account\n");
    }

    @Override
    public int getInput() {
        return getMenu().createMenu(AccountType.values()) - 1;
    }
}
