package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AbstractAccount;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.menu.MainMenuController;

import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        String persistenceUnit = "dev";

        JpaSessionManager sessionManager = new JpaSessionManager(Persistence.createEntityManagerFactory(persistenceUnit));
        JpaTransactionManager transactionManager = new JpaTransactionManager(sessionManager);

        CustomerDao<Customer> customerDao = new JpaCustomerDao(sessionManager);
        AccountDao<AbstractAccount> accountDao = new JpaAccountDao(sessionManager);

        JpaAuthenticationService authenticationService = new JpaAuthenticationService();
        JpaCustomerService customerService = new JpaCustomerService(transactionManager, customerDao);
        JpaAccountService accountService = new JpaAccountService(authenticationService, transactionManager, accountDao);

        Controller controller = new MainMenuController(customerService, accountService, authenticationService);
        controller.execute();
    }
}
