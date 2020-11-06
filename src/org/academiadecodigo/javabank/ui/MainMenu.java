package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;

public class MainMenu extends Menu {
    public MainMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    public void init() {
        createMenu(new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.DELETECUSTOMERS});

        super.init();
    }
}
