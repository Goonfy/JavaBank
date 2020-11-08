package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;

public class MainMenu extends Menu {
    public MainMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    public void init() {
        super.init();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};
        int option = createMenu(menuItems) - 1;
        switch (menuItems[option]) {
            case NEWCUSTOMER:
                addNewCustomer();
                break;
            case SHOWCUSTOMERS:
                showCustomers();
                break;
            case EDITCUSTOMERS:
                editCustomers();
                break;
            case REMOVECUSTOMERS:
                removeCustomers();
                break;
            case EXIT:
                System.exit(0);
                break;
        }

        init();
    }

    private void addNewCustomer() {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("Type in a name: ");

        StringInputScanner questionEmail = new StringInputScanner();
        questionEmail.setMessage("Type in an email: ");

        StringInputScanner questionPhoneNumber = new StringInputScanner();
        questionPhoneNumber.setMessage("Type in a Phone Number: ");

        String name = getPrompt().getUserInput(questionName);
        String email = getPrompt().getUserInput(questionEmail);
        String phoneNumber = getPrompt().getUserInput(questionPhoneNumber);

        getMenuHandler().addNewCustomer(name, email, phoneNumber);
    }

    private void showCustomers() {
        System.out.println("\n" + getBank().getAllCustomersInfo());
    }

    private void editCustomers() {
        Menu menu = new EditCustomerMenu(getPrompt(), getBank());
        menu.init();
    }

    private void removeCustomers() {
        int customerId = createCustomerMenu();
        getMenuHandler().removeCustomer(customerId);
    }
}
