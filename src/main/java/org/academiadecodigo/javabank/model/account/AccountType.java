package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.Descriptable;

/**
 * The possible {@link Account} types
 */
public enum AccountType implements Descriptable {

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

    @Override
    public String getDescription() {
        return description;
    }
}
