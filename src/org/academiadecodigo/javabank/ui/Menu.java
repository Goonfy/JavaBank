package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

public class Menu {

    private final Bank bank;
    private final MenuHandler menuHandler;

    private final Prompt prompt;

    public Menu(Bank bank) {
        this.bank = bank;

        prompt = new Prompt(System.in, System.out);
        menuHandler = new MenuHandler(bank, this);
    }

    public void init() {
        int option = createMenuWithOptions(new String[]{"Add new Customer", "Show Customers",
                "Delete Customers", "Add Account", "Close Account", "Transfer Money",
                "Deposit Money", "Withdraw Money"});

        switch (MenuItem.values()[option - 1]) {
            case NEWCUSTOMER:
                menuHandler.addNewCustomer();
                break;
            case SHOWCUSTOMER:
                System.out.println("\n" + bank.getAllCustomersInfo());
                break;
            case DELETECUSTOMER:
                menuHandler.deleteCustomers();
                break;
            case ADDACCOUNT:
                menuHandler.addAccount();
                break;
            case SHOWACCOUNT:
                Customer customer = bank.getCustomerFromID(selectCustomer());
                System.out.println("\n" + customer.getAllAccountsInfo());
                break;
            case CLOSEACCOUNT:
                menuHandler.closeAccount();
                break;
            case TRANSFERMONEY:
                menuHandler.transferMoney();
                break;
            case DEPOSITMONEY:
                menuHandler.depositMoney();
                break;
            case WITHDRAWMONEY:
                menuHandler.withdrawMoney();
                break;
            default:
                System.out.println("Invalid option...");
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        init();
    }

    protected int createMenuWithOptions(String[] options) {
        MenuInputScanner menuInputScanner = new MenuInputScanner(options);
        menuInputScanner.setMessage("Please choose an option: ");

        return prompt.getUserInput(menuInputScanner);
    }

    protected int createSelectionInput(String message) {
        IntegerInputScanner chooseCustomerOption = new IntegerInputScanner();
        chooseCustomerOption.setMessage(message);
        return prompt.getUserInput(chooseCustomerOption);
    }

    protected int selectCustomer() {
        System.out.println("\n" + bank.getAllCustomersInfo());

        return createSelectionInput("Choose one customer from the list: ");
    }

    protected int selectAccount(Customer customer) {
        System.out.println("\n" + customer.getAllAccountsInfo());

        return createSelectionInput("Choose one account from the list: ");
    }
}
