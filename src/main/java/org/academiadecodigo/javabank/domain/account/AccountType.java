package org.academiadecodigo.javabank.domain.account;

import org.academiadecodigo.javabank.Descriptable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
