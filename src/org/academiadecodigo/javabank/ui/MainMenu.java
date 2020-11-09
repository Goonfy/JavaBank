package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.operations.Operation;
import org.academiadecodigo.javabank.ui.operations.account.*;
import org.academiadecodigo.javabank.ui.operations.customer.AddNewCustomer;
import org.academiadecodigo.javabank.ui.operations.customer.EditCustomer;
import org.academiadecodigo.javabank.ui.operations.customer.RemoveCustomer;
import org.academiadecodigo.javabank.ui.operations.customer.ShowCustomer;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu extends Menu {
    public MainMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    public void init() {
        super.init();

        Map<Integer, Operation> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        menuMap.put(1, new AddNewCustomer(-1, getPrompt(), getBank(), this));
        menuMap.put(2, new ShowCustomer(-1, getPrompt(), getBank(), this));
        menuMap.put(3, new EditCustomer(-1, getPrompt(), getBank(), this));
        menuMap.put(4, new RemoveCustomer(-1, getPrompt(), getBank(), this));

        int option = createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();

        init();
    }
}
