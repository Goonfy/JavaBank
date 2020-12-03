package org.academiadecodigo.javabank.persistence.model.account;

/**
 * The possible {@link Account} types
 */
public enum AccountType {

    /**
     * @see CheckingAccount
     */
    CHECKING("Checking Type Account"),

    /**
     * @see SavingsAccount
     */
    SAVINGS("Savings Type Account");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
