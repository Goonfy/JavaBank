package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.CostumerController;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(new AccountManager());

        Controller controller = new MainMenuController(bank);
        controller.execute();
    }
}
