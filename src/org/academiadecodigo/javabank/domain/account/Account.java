package org.academiadecodigo.javabank.domain.account;

/**
 * A generic account domain entity to be used as a base for concrete types of accounts
 */
public abstract class Account {

    private int id;
    private double balance = 0;

    /**
     * Initializes a new {@code Account} instance with an id
     *
     * @param id the account id
     */
    public Account(int id) {
        this.id = id;
    }

    /**
     * Gets the account id
     *
     * @return the account id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the account balance
     *
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the account type
     *
     * @return the account type
     */
    public abstract AccountType getAccountType();

    public String toString() {
        return "\n" + id + " - [ Balance: " + balance + ", Type: " + getAccountType() + " ]";
    }
}
