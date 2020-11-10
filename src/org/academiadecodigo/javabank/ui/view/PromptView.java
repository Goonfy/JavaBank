package org.academiadecodigo.javabank.ui.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.Descriptable;

public abstract class PromptView implements View {

    private final Prompt prompt;

    public PromptView() {
        this.prompt = new Prompt(System.in, System.out);
    }

    public <T extends Descriptable> int createMenu(T[] menuItems) {
        String[] menuDescriptions = new String[menuItems.length];
        for (int i = 0; i < menuItems.length; i++) {
            menuDescriptions[i] = menuItems[i].getDescription();
        }

        MenuInputScanner menuInputScanner = new MenuInputScanner(menuDescriptions);
        menuInputScanner.setMessage("Please choose an option: ");
        return prompt.getUserInput(menuInputScanner);
    }

    public int createSelectionInput(String message) {
        IntegerInputScanner chooseCustomerOption = new IntegerInputScanner();
        chooseCustomerOption.setMessage(message);
        return prompt.getUserInput(chooseCustomerOption);
    }

    public int createCustomerMenu() {
        return createSelectionInput("Choose one customer from the list: ");
    }

    public int createAccountMenu(Customer customer) {
        if (customer == null) {
            return -1;
        }

        System.out.println("\n" + customer.getAccountManager().getAllAccountsInfo());
        if (!customer.getAccountManager().getAllAccountsInfo().contains("[")) {
            return -1;
        }

        return createSelectionInput("Choose one account from the list: ");
    }

    public Prompt getPrompt() {
        return prompt;
    }
}
