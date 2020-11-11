package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * The customer domain entity
 */
public class Customer {

    private int id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    private final Map<Integer, Account> accounts = new HashMap<>();

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the balance of an {@link Account}
     *
     * @param id the id of the account
     * @return the account balance
     */
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    /**
     * Gets the total customer balance
     *
     * @return the customer balance
     */
    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "\n" + id + " - [ Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber + " ]";
    }
}
