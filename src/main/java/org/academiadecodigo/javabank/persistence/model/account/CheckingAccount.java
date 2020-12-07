package org.academiadecodigo.javabank.persistence.model.account;

import org.academiadecodigo.javabank.persistence.model.Customer;

import javax.persistence.Entity;

/**
 * A checking account with no restrictions
 * @see Account
 * @see AccountType#CHECKING
 */
@Entity
public class CheckingAccount extends AbstractAccount {

    /**
     * Creates a new {@code CheckingAccount} instance
     *
     * @see Account#Account(int)
     */
    public CheckingAccount() {
    }

    public CheckingAccount(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
