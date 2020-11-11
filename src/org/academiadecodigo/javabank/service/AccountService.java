package org.academiadecodigo.javabank.service;

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
    private final Map<Integer, Account> accountMap;

    /**
     * Creates a new {@code AccountManager}
     */
    public AccountService() {
        this.accountMap = new HashMap<>();
    }

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

        accountMap.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    public void close(Account account) {
        accountMap.remove(account.getId());
    }

    /**
     * Perform an {@link Account} deposit if possible
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    @Override
    public void deposit(int id, double amount) {
        if (canCredit(amount)) {
            accountMap.get(id).setBalance(amount);
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

        Account account = accountMap.get(id);

        if (!canWithdraw(account)) {
            return;
        }

        accountMap.get(id).setBalance(amount);
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

        Account srcAccount = accountMap.get(srcId);

        // make sure transaction can be performed
        if (canDebit(srcAccount, amount) && canCredit(amount)) {
            withdraw(srcId, amount);
            deposit(dstId, amount);
        }
    }

    public Account get(int id) {
        if (accountMap.get(id) == null) {
            return null;
        }

        return accountMap.get(id);
    }

    public String getAllAccountsInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Account account : accountMap.values()) {
            stringBuilder.append(account.toString());
        }

        return stringBuilder.toString();
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
    public boolean canDebit(Account account, double amount) {
        return amount > 0 && amount <= account.getBalance();
    }

    /**
     * Checks if the account can be withdrawn
     *
     * @return {@code true} if withdraw can be done
     */
    public boolean canWithdraw(Account account) {
        return account.getAccountType() == AccountType.CHECKING;
    }
}
