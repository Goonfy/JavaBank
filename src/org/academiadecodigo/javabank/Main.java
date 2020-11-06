package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.ui.MainMenu;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.MenuItem;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(new AccountManager());

        Menu menu = new MainMenu(new Prompt(System.in, System.out), bank);
        menu.init();
    }
}
