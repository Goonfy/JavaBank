package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Responsible for account management
 */
public class AccountService implements AccountServiceInterface {

    private static int numberAccounts = 0;
    private final Map<Integer, Account> accounts = new HashMap<>();

    private Customer customer;

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     */
    @Override
    public Account add(AccountType accountType) {

        Account newAccount;
        numberAccounts++;

        if (accountType == AccountType.CHECKING) {
            newAccount = new CheckingAccount(numberAccounts);
        } else {
            newAccount = new SavingsAccount(numberAccounts);
        }

        accounts.put(newAccount.getId(), newAccount);

        return customer.addAccount(newAccount.getId(), newAccount);
    }

    @Override
    public void close(int id) {
        accounts.remove(id);
        customer.removeAccount(id);
    }

    /**
     * Perform an {@link Account} deposit if possible
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    @Override
    public void deposit(int id, double amount) {
        Account account = customer.getAccount(id);

        if (account.canCredit(amount)) {
            customer.getAccount(id).addBalance(amount);
        }
    }

    /**
     * Perform an {@link Account} withdrawal if possible
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     */
    @Override
    public void withdraw(int id, double amount) {

        Account account = customer.getAccount(id);

        if (!account.canWithdraw()) {
            return;
        }

        customer.getAccount(id).addBalance(amount);
    }

    /**
     * Performs a transfer between two {@link Account} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     */
    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = customer.getAccount(srcId);
        Account dstAccount = customer.getAccount(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            withdraw(srcId, amount);
            deposit(dstId, amount);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBalanceFromAllAccounts() {
        int balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }
}
