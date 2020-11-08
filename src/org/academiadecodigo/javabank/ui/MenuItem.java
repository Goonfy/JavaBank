package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.javabank.domain.Customer;

public enum MenuItem {
    NEWCUSTOMER("Add new Customer"),
    SHOWCUSTOMERS("Show Customers"),
    EDITCUSTOMERS("Edit Customers"),
    REMOVECUSTOMERS("Delete Customers"),
    ADDACCOUNT("Add Account"),
    SHOWACCOUNTS("Show Accounts"),
    CLOSEACCOUNTS("Close Accounts"),
    TRANSFERMONEY("Transfer Money"),
    DEPOSITMONEY("Deposit Money"),
    WITHDRAWMONEY("Withdraw Money"),
    CHECKINGACCOUNT("Checking Account Type"),
    SAVINGSACCOUNT("Savings Account Type"),
    BACK("Back"),
    EXIT("Exit");

    private final String menuDescription;

    MenuItem(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuDescription() {
        return menuDescription;
    }
}
