package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for account management
 */
public class AccountService implements AccountServiceInterface {

    private static int numberAccounts = 0;
    private final Map<Integer, Account> accounts = new HashMap<>();

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     */
    @Override
    public Account add(Customer customer, AccountType accountType) {

        Account newAccount;
        numberAccounts++;

        if (accountType == AccountType.CHECKING) {
            newAccount = new CheckingAccount(numberAccounts);
        } else {
            newAccount = new SavingsAccount(numberAccounts);
        }

        return customer.addAccount(newAccount.getId(), newAccount);
    }

    public void close(Customer customer, Account account) {
        customer.getAccounts().remove(account.getId());
    }

    /**
     * Perform an {@link Account} deposit if possible
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    @Override
    public void deposit(Customer customer, int id, double amount) {
        Account account = customer.getAccounts().get(id);

        if (account.canCredit(amount)) {
            customer.getAccounts().get(id).changeBalance(amount);
        }
    }

    /**
     * Perform an {@link Account} withdrawal if possible
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     */
    @Override
    public void withdraw(Customer customer, int id, double amount) {

        Account account = customer.getAccounts().get(id);

        if (!account.canWithdraw()) {
            return;
        }

        customer.getAccounts().get(id).changeBalance(amount);
    }

    /**
     * Performs a transfer between two {@link Account} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     */
    @Override
    public void transfer(Customer customer, int srcId, int dstId, double amount) {

        Account srcAccount = customer.get(srcId);
        Account dstAccount = customer.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            withdraw(customer, srcId, amount);
            deposit(customer, dstId, amount);
        }
    }
}
