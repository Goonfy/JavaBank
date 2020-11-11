package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;

public class Main {
    public static void main(String[] args) {
        AccountService customerServiceImplementation = new CustomerService(new AccountService());

        Controller controller = new MainMenuController(customerServiceImplementation);
        controller.execute();
    }
}
