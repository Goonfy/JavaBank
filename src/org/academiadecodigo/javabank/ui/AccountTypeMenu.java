package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;

public class AccountTypeMenu extends Menu {

    public AccountTypeMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    @Override
    public void init() {
        createMenu(new MenuItem[]{MenuItem.CHECKINGACCOUNT, MenuItem.SAVINGSACCOUNT});

        super.init();
    }
}
