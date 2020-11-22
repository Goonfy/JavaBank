package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.internal.SessionFactoryImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public class App {
    public static void main(String[] args) {
        String persistenceUnit = "dev";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);

        Controller controller = new MainMenuController(new CustomerService(entityManagerFactory), new AccountService(entityManagerFactory), new AuthenticationService(entityManagerFactory));
        controller.execute();
    }
}
