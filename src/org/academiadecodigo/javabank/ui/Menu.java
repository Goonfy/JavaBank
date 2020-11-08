package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;

import java.util.Arrays;

public abstract class Menu {

    private final Bank bank;
    private final MenuHandler menuHandler;

    private final Prompt prompt;

    public Menu(Prompt prompt, Bank bank) {
        this.prompt = prompt;
        this.bank = bank;

        menuHandler = new MenuHandler(bank);
    }

    public void init() {
        try {
            System.out.println("\nPlease wait...\n");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    protected int createMenu(MenuItem[] menuItems) {
        String[] menuDescriptions = new String[menuItems.length];
        for (int i = 0; i < menuItems.length; i++) {
            menuDescriptions[i] = menuItems[i].getMenuDescription();
        }

        MenuInputScanner menuInputScanner = new MenuInputScanner(menuDescriptions);
        menuInputScanner.setMessage("Please choose an option: ");
        return prompt.getUserInput(menuInputScanner);
    }

    protected int createMenu(AccountType[] accountTypes) {
        String[] menuDescriptions = new String[accountTypes.length];
        for (int i = 0; i < accountTypes.length; i++) {
            menuDescriptions[i] = accountTypes[i].getDescription();
        }

        MenuInputScanner menuInputScanner = new MenuInputScanner(menuDescriptions);
        menuInputScanner.setMessage("Please choose an option: ");
        return prompt.getUserInput(menuInputScanner);
    }

    protected int createSelectionInput(String message) {
        IntegerInputScanner chooseCustomerOption = new IntegerInputScanner();
        chooseCustomerOption.setMessage(message);
        return prompt.getUserInput(chooseCustomerOption);
    }

    protected int createCustomerMenu() {
        System.out.println("\n" + bank.getAllCustomersInfo());
        if (!bank.getAllCustomersInfo().contains("[")) {
            return -1;
        }

        return createSelectionInput("Choose one customer from the list: ");
    }

    protected int createAccountMenu(Customer customer) {
        if (customer == null) {
            return -1;
        }

        System.out.println("\n" + customer.getAllAccountsInfo());
        if (!customer.getAllAccountsInfo().contains("[")) {
            return -1;
        }

        return createSelectionInput("Choose one account from the list: ");
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public Bank getBank() {
        return bank;
    }

    public MenuHandler getMenuHandler() {
        return menuHandler;
    }
}
