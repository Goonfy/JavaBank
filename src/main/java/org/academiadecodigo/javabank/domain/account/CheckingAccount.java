package org.academiadecodigo.javabank.domain.account;

import javax.persistence.Column;
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
        super();
    }

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
