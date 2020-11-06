package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.ui.Menu;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(new AccountManager());

        Menu menu = new Menu(bank);
        menu.init();
    }
}
