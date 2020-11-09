package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.operations.account.*;

import java.util.LinkedHashMap;
import java.util.Map;

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

        Map<Integer, Operation> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        menuMap.put(1, new AddAccount(customerId, getPrompt(), getBank(), this));
        menuMap.put(2, new CloseAccount(customerId, getPrompt(), getBank(), this));
        menuMap.put(3, new ShowAccount(customerId, getPrompt(), getBank(), this));
        menuMap.put(4, new TransferMoney(customerId, getPrompt(), getBank(), this));
        menuMap.put(5, new DepositMoney(customerId, getPrompt(), getBank(), this));
        menuMap.put(6, new WithdrawMoney(customerId, getPrompt(), getBank(), this));

        int option = createMenu(menuItems) - 1;
        menuMap.get(option).execute();
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
