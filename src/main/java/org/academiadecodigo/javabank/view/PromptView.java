package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.Descriptable;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaCustomerService;

import java.util.List;

public abstract class PromptView implements View {

    private Prompt prompt;

    public PromptView(Prompt prompt) {
        this.prompt = prompt;
    }

    public void execute() {
        /*try {
            System.out.println("\nPlease wait...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }*/
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

    public int createCustomerMenu(CustomerService customerService) {
        List<Customer> customers = customerService.listAll();
        System.out.println("\n" + customers.toString());
        if (customers.isEmpty()) {
            return -1;
        }

        return createSelectionInput("Choose one customer from the list: ");
    }

    public int createAccountMenu(AccountService accountService, Customer customer) {
        List<AbstractAccount> accounts = accountService.getAllAccountsInfoFrom(customer);
        System.out.println("\n" + accounts.toString());
        if (accounts.isEmpty()) {
            return -1;
        }

        return createSelectionInput("Choose one account from the list: ");
    }

    public int getAmount() {
        return createSelectionInput("Please enter the amount of money: ");
    }

    public String getName() {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("Type in a name: ");
        return getPrompt().getUserInput(questionName);
    }

    public String getEmail() {
        StringInputScanner questionEmail = new StringInputScanner();
        questionEmail.setMessage("Type in an email: ");
        return getPrompt().getUserInput(questionEmail);
    }

    public String getPhone() {
        StringInputScanner questionPhoneNumber = new StringInputScanner();
        questionPhoneNumber.setMessage("Type in a Phone Number: ");
        return getPrompt().getUserInput(questionPhoneNumber);
    }

    public Prompt getPrompt() {
        return prompt;
    }
}
