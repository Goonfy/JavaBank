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

    private final Prompt prompt;
    private final Bank bank;
    private final Menu menu;

    public MenuHandler(Prompt prompt, Bank bank, Menu menu) {
        this.prompt = prompt;
        this.bank = bank;
        this.menu = menu;
    }

    protected void addNewCustomer(String name, String email, String phoneNumber) {
        Customer customer = new Customer(name, email, phoneNumber);
        bank.addCustomer(customer);

        System.out.println("\nAdded customer " + name + " with id " + customer.getId() + "\n");
    }

    protected void removeCustomer(int customerNumber) {
        bank.removeCustomer(bank.getCustomerFromID(customerNumber));

        System.out.println("\nRemoved customer successfully" + "\n");
    }

    protected void addAccount(int customerNumber) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        AccountType accountType = AccountType.values()[customerNumber];
        customer.openAccount(accountType);

        System.out.println("\nAdded new account successfully\n");
    }

    protected void closeAccount(int customerNumber, int accountNumber) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        customer.closeAccount(customer.getAccountFromID(accountNumber);

        System.out.println("\nSuccessfully closed this account\n");
    }

    protected void transferMoney(int customerNumber, int accountNumber, int customerToSendMoneyTo, int accountToSendMoneyTo, int amountToTransfer) {
        Customer customer = bank.getCustomerFromID(customerNumber);

        System.out.println("\n" + customer.getAllAccountsInfo());
        int chosenAccount = menu.createSelectionInput("Choose one account from the list to use to transfer: ");

        System.out.println("\n" + bank.getAllCustomersInfo());
        int chosenUserId = menu.createSelectionInput("Choose one customer from the list to transfer money to: ");

        System.out.println("\n" + bank.getCustomerFromID(chosenUserId).getAllAccountsInfo());
        int chosenUserAccount = menu.createSelectionInput("Choose the account of the customer from the list to " +
                "transfer money to: ");

        DoubleInputScanner inputScanner = new DoubleRangeInputScanner(1, customer.getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + bank.getCustomerFromID(chosenUserId).getName() + ": ");
        double amountOfMoney = prompt.getUserInput(inputScanner);
        customer.getAccountManager().transfer(accountNumber, accountToSendMoneyTo, amountToTransfer);

        System.out.println("Successfully sent money to " + bank.getCustomerFromID(chosenUserId).getName());
    }

    protected void depositMoney(int customerNumber, int accountNumber, int amountToDeposit) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        Account account = customer.getAccountFromID(accountNumber);
        int amountToDeposit = menu.createSelectionInput("Please enter the amount of money you want to deposit: ");
        account.credit(amountToDeposit);

        System.out.println("\nDeposited money to account successfully\n");
    }

    protected void withdrawMoney(int customerNumber, int accountNumber, int amountToWithdraw) {
        Customer customer = bank.getCustomerFromID(customerNumber);
        Account account = customer.getAccountFromID(accountNumber);
        int amountToDeposit = menu.createSelectionInput("Please enter the amount of money you want to withdraw: ");
        account.debit(amountToWithdraw);

        System.out.println("\nWithdrew money from account successfully\n");
    }
}
