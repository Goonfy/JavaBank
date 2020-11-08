package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

public class MenuHandler {

    private final Bank bank;

    public MenuHandler(Bank bank) {
        this.bank = bank;
    }

    protected void addNewCustomer(String name, String email, String phoneNumber) {
        bank.addCustomer(new Customer(name, email, phoneNumber));
    }

    protected void removeCustomer(int customerNumber) {
        bank.removeCustomer(bank.getCustomerFromID(customerNumber));
    }

    protected void addAccount(int customerNumber, AccountType accountType) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        if (customer == null) {
            System.out.println("\nError adding new account\n");
            return;
        }

        customer.openAccount(accountType);

        System.out.println("\nAdded new account successfully\n");
    }

    protected void closeAccount(int customerNumber, int accountNumber) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        if (customer == null) {
            System.out.println("\nError closing account\n");
            return;
        }

        customer.closeAccount(customer.getAccountFromID(accountNumber));

        System.out.println("\nSuccessfully closed this account\n");
    }

    protected void transferMoney(int customerNumber, int accountNumber, int accountToSendMoneyTo, int amountToTransfer) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        if (customer == null) {
            System.out.println("\nError transferring money\n");
            return;
        }
        customer.getAccountManager().transfer(accountNumber, accountToSendMoneyTo, amountToTransfer);

        System.out.println("Successfully sent money to " + bank.getCustomerFromID(accountToSendMoneyTo).getName());
    }

    protected void depositMoney(int customerNumber, int accountNumber, int amountToDeposit) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        if (customer == null) {
            System.out.println("\nError depositing money\n");
            return;
        }

        Account account = customer.getAccountFromID(accountNumber);
        account.credit(amountToDeposit);

        System.out.println("\nDeposited money to account successfully\n");
    }

    protected void withdrawMoney(int customerNumber, int accountNumber, int amountToWithdraw) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        if (customer == null) {
            System.out.println("\nError withdrawing money\n");
            return;
        }

        Account account = customer.getAccountFromID(accountNumber);
        account.debit(amountToWithdraw);

        System.out.println("\nWithdrew money from account successfully\n");
    }
}
