package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.menu.MainMenuController;
import org.academiadecodigo.javabank.ui.view.menu.MainMenuView;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(new AccountManager());

        OperationController operationController = new MainMenuController(bank);
        operationController.execute();
    }
}
