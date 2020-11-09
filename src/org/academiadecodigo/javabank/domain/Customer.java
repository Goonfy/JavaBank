package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Map;

/**
 * The customer domain entity
 */
public class Customer {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    private AccountManager accountManager;
    private final Map<Integer, Account> accounts = new HashMap<>();

    public Customer() {}

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the account manager
     *
     * @param accountManager the account manager to set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Opens a new account
     *
     * @param accountType the account type to be opened
     * @return the new account id
     * @see AccountManager#openAccount(AccountType)
     */
    public int openAccount(AccountType accountType) {
        Account account = accountManager.openAccount(accountType);
        accounts.put(account.getId(), account);
        return account.getId();
    }

    public void closeAccount(Account account) {
        accounts.remove(account.getId());
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

    public void setId() {
        id++;
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

    public Account getAccountFromID(int id) {
        return accounts.get(id);
    }

    public String getAllAccountsInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Account account : accounts.values()) {
            stringBuilder.append(account.toString());
        }

        return stringBuilder.toString();
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    @Override
    public String toString() {
        return id + " - [ Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber + " ]\n";
    }
}
