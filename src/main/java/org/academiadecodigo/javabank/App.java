package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;

import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        String persistenceUnit = "dev";

        JpaSessionManager sessionManager = new JpaSessionManager(Persistence.createEntityManagerFactory(persistenceUnit));
        JpaTransactionManager transactionManager = new JpaTransactionManager(sessionManager);

        AuthenticationService authenticationService = new AuthenticationService();
        CustomerService customerService = new CustomerService(transactionManager, sessionManager);
        AccountService accountService = new AccountService(authenticationService, transactionManager, sessionManager);

        Controller controller = new MainMenuController(customerService, accountService, authenticationService);
        controller.execute();
    }
}
