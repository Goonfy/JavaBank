package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

import java.util.HashMap;
import java.util.Map;

/**
 * The customer domain entity
 */
public class Customer {

    private final int id;
    private final String name;
    private final String email;
    private final String phoneNumber;

    private final Map<Integer, Account> accounts = new HashMap<>();

    public Customer(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Account addAccount(int id, Account account) {
        return accounts.put(id, account);
    }

    public void removeAccount(int id) {
        accounts.remove(id);
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
    public double getTotalBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public Account getAccount(int id) {
        if (accounts.get(id) == null) {
            return null;
        }

        return accounts.get(id);
    }

    public String getAllAccountsInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Account account : accounts.values()) {
            stringBuilder.append(account.toString());
        }

        return stringBuilder.toString();
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

    @Override
    public String toString() {
        return "\n" + id + " - [ Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber + " ]";
    }
}
