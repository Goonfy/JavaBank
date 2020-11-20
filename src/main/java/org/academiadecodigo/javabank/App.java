package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;

public class App {
    public static void main(String[] args) {
        Controller controller = new MainMenuController(new CustomerService(), new AccountService(), new AuthenticationService());
        controller.execute();
    }
}
