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

    public void addBalance(double balance) {
        this.balance += balance;
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

    /**
     * Checks if a specific amount can be credited on the account
     *
     * @param amount the amount to check
     * @return {@code true} if the account can be credited
     */
    public boolean canCredit(double amount) {
        return amount > 0;
    }

    /**
     * Checks if a specific amount can be debited from the account
     *
     * @param amount the amount to check
     * @return {@code true} if the account can be debited
     */
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= getBalance();
    }

    /**
     * Checks if the account can be withdrawn
     *
     * @return {@code true} if withdraw can be done
     */
    public boolean canWithdraw() {
        return getAccountType() == AccountType.CHECKING;
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
