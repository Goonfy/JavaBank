package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
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

    public MenuHandler(Bank bank, Menu menu) {
        this.bank = bank;
        this.menu = menu;
        prompt = new Prompt(System.in, System.out);
    }

    protected void addNewCustomer() {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("Type in a name: ");
        StringInputScanner questionEmail = new StringInputScanner();
        questionEmail.setMessage("Type in an email: ");
        StringInputScanner questionPhoneNumber = new StringInputScanner();
        questionPhoneNumber.setMessage("Type in a Phone Number: ");

        String name = prompt.getUserInput(questionName);
        String email = prompt.getUserInput(questionEmail);
        String phoneNumber = prompt.getUserInput(questionPhoneNumber);

        Customer customer = new Customer();
        customer.setup(name, email, phoneNumber);
        bank.addCustomer(customer);

        System.out.println("\nAdded customer " + name + "\n");
    }

    protected void deleteCustomers() {
        bank.removeCustomer(bank.getCustomerFromID(menu.selectCustomer()));
        System.out.println("\nRemoved customer successfully" + "\n");
    }

    protected void addAccount() {
        int option = menu.selectCustomer();
        Customer customer = bank.getCustomerFromID(option);

        option = menu.createMenuWithOptions(new String[]{"Checking Type", "Saving Type"});
        AccountType accountType;
        switch (AccountType.values()[option - 1]) {
            case CHECKING:
                accountType = AccountType.CHECKING;
                break;
            case SAVINGS:
                accountType = AccountType.SAVINGS;
                break;
            default:
                System.out.println("Invalid option...");
                return;
        }

        customer.openAccount(accountType);

        System.out.println("\nAdded new account successfully\n");
    }

    protected void closeAccount() {
        Customer customer = bank.getCustomerFromID(menu.selectCustomer());
        customer.closeAccount(customer.getAccountFromID(menu.selectAccount(customer)));

        System.out.println("\nSuccessfully closed this account\n");
    }

    protected void transferMoney() {
        Customer customer = bank.getCustomerFromID(menu.selectCustomer());

        System.out.println("\n" + customer.getAllAccountsInfo());
        int chosenAccount = menu.createSelectionInput("Choose one of your accounts from the list to transfer: ");

        System.out.println("\n" + bank.getAllCustomersInfo());
        int chosenUserId = menu.createSelectionInput("Choose one customer from the list to transfer money to: ");

        System.out.println("\n" + bank.getCustomerFromID(chosenUserId).getAllAccountsInfo());
        int chosenUserAccount = menu.createSelectionInput("Choose the account of the customer from the list to " +
                "transfer money to: ");

        DoubleInputScanner inputScanner = new DoubleRangeInputScanner(1, customer.getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + bank.getCustomerFromID(chosenUserId).getName() + ": ");
        double amountOfMoney = prompt.getUserInput(inputScanner);

        customer.getAccountManager().transfer(chosenAccount, chosenUserAccount, amountOfMoney);

        System.out.println("Successfully sent money to " + bank.getCustomerFromID(chosenUserId).getName());
    }

    protected void depositMoney() {
        Customer customer = bank.getCustomerFromID(menu.selectCustomer());
        Account account = customer.getAccountFromID(menu.selectAccount(customer));
        int amountToDeposit = menu.createSelectionInput("Please enter the amount of money you want to deposit: ");
        account.credit(amountToDeposit);

        System.out.println("\nDeposited money to account successfully\n");
    }

    protected void withdrawMoney() {
        Customer customer = bank.getCustomerFromID(menu.selectCustomer());
        Account account = customer.getAccountFromID(menu.selectAccount(customer));
        int amountToDeposit = menu.createSelectionInput("Please enter the amount of money you want to withdraw: ");
        account.debit(amountToDeposit);

        System.out.println("\nWithdrew money from account successfully\n");
    }
}
