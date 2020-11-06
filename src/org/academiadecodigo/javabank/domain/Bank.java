package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private static int NUMBER_OF_CUSTOMERS;

    private final AccountManager accountManager;
    private final Set<Customer> customers = new LinkedHashSet<>();

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        NUMBER_OF_CUSTOMERS++;

        customers.add(customer);
        customer.setAccountManager(accountManager);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public Customer getCustomerFromID(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }

    public String getAllCustomersInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Customer customer : customers) {
            String info = customer.getId() + " - [ Name: " + customer.getName() + ", Email: "
                    + customer.getEmail() + ", Phone Number: " + customer.getPhoneNumber() + " ]\n";
            stringBuilder.append(info);
        }

        return stringBuilder.toString();
    }

    public static int getNumberOfCustomers() {
        return NUMBER_OF_CUSTOMERS;
    }
}
