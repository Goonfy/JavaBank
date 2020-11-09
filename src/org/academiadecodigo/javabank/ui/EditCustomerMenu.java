package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleRangeInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;

public class EditCustomerMenu extends Menu {

    public EditCustomerMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    @Override
    public void init() {
        super.init();

        int customerId = createCustomerMenu();
        if (customerId == -1) {
            return;
        }

        MenuItem[] menuItems = new MenuItem[]{MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK};

        int option = createMenu(menuItems) - 1;
        switch (menuItems[option]) {
            case ADDACCOUNT:
                addAccount(customerId);
                break;
            case SHOWACCOUNTS:
                showAccounts(customerId);
                break;
            case CLOSEACCOUNTS:
                closeAccount(customerId);
                break;
            case TRANSFERMONEY:
                transferMoney(customerId);
                break;
            case DEPOSITMONEY:
                depositMoney(customerId);
                break;
            case WITHDRAWMONEY:
                withdrawMoney(customerId);
                break;
            case BACK:
                Menu menu = new MainMenu(getPrompt(), getBank());
                menu.init();
                break;
        }
    }

    private void addAccount(int customerId) {
        Customer customer = getBank().getCustomerFromID(customerId);
        if (customer != null) {
            int option = createMenu(AccountType.values()) - 1;
            getMenuHandler().addAccount(customer.getId(), AccountType.values()[option]);
        }
    }

    private void showAccounts(int customerId) {
        System.out.println("\n" + getBank().getCustomerFromID(customerId).getAllAccountsInfo());
    }

    private void closeAccount(int customerId) {
        int accountId = createAccountMenu(getBank().getCustomerFromID(customerId));
        if (accountId == -1) {
            return;
        }

        getMenuHandler().closeAccount(customerId, accountId);
    }

    private void transferMoney(int customerId) {
        int accountId = createAccountMenu(getBank().getCustomerFromID(customerId));
        if (accountId == -1) {
            return;
        }

        int customerIdToTransferMoneyTo = createCustomerMenu();
        int accountIdToTransferMoneyTo = createAccountMenu(getBank().getCustomerFromID(customerIdToTransferMoneyTo));

        IntegerInputScanner inputScanner = new IntegerRangeInputScanner(1, (int) getBank().getBalance());
        inputScanner.setMessage("How much money do you want to transfer to "
                + getBank().getCustomerFromID(customerId).getName() + ": ");
        int amountOfMoney = getPrompt().getUserInput(inputScanner);

        getMenuHandler().transferMoney(customerId, accountId,
                accountIdToTransferMoneyTo, amountOfMoney);
    }

    private void depositMoney(int customerId) {
        int accountId = createAccountMenu(getBank().getCustomerFromID(customerId));
        if (accountId == -1) {
            return;
        }

        int amountToDeposit = createSelectionInput("Please enter the amount of money you want to deposit: ");
        getMenuHandler().depositMoney(customerId, accountId, amountToDeposit);
    }

    private void withdrawMoney(int customerId) {
        int accountId = createAccountMenu(getBank().getCustomerFromID(customerId));
        if (accountId == -1) {
            return;
        }

        int amountToWithdraw = createSelectionInput("Please enter the amount of money you want to withdraw: ");

        getMenuHandler().withdrawMoney(customerId, accountId, amountToWithdraw);
    }
}
