package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private final Prompt prompt;

    private final Bank bank;

    public Menu(Bank bank) {
        this.bank = bank;
        prompt = new Prompt(System.in, System.out);
    }

    private MenuItem getMenuChosenOption(String[] options) {
        MenuInputScanner menuInputScanner = new MenuInputScanner(options);
        menuInputScanner.setMessage("Please choose an option: ");

        int option = prompt.getUserInput(menuInputScanner);

        return MenuItem.values()[option - 1];
    }

    public void init() {
        MenuItem chosenOption = getMenuChosenOption(new String[]{"Add new Customer", "Show Customers",
                "Edit Customers", "Delete Customers"});

        switch (chosenOption) {
            case NEW:
                addNewCustomer();
                break;
            case SHOW:
                showCustomers();
                break;
            case EDIT:
                editCustomers();
                break;
            case DELETE:
                deleteCustomers();
                break;
            default:
                System.out.println("Invalid option...");
                init();
        }
    }

    private void addNewCustomer() {


        Customer customer = new Customer();
        //customer.openAccount()
        bank.addCustomer(customer);
    }

    private void showCustomers() {

    }

    private void editCustomers() {

    }

    private void deleteCustomers() {

    }

    private void listAllCustomers() {

    }
}
