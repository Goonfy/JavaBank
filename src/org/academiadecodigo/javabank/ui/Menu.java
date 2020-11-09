package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

import java.util.Arrays;

public abstract class Menu {

    private final Bank bank;
    private final MenuHandler menuHandler;

    private final Prompt prompt;

    public Menu(Prompt prompt, Bank bank) {
        this.prompt = prompt;
        this.bank = bank;

        menuHandler = new MenuHandler(prompt, bank, this);
    }

    public void init() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        init();
    }

    protected int createMenu(MenuItem[] menuItems) {
        String[] menuDescriptions = new String[menuItems.length];
        for (MenuItem menuItem : menuItems) {
            Arrays.fill(menuDescriptions, menuItem.getMenuDescription());
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
        return createSelectionInput("Choose one customer from the list: ");
    }

    protected int createAccountMenu(Customer customer) {
        System.out.println("\n" + customer.getAllAccountsInfo());
        return createSelectionInput("Choose one account from the list: ");
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public MenuHandler getMenuHandler() {
        return menuHandler;
    }
}
