package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.customer.AddNewCustomerController;
import org.academiadecodigo.javabank.ui.controller.customer.EditCustomerController;
import org.academiadecodigo.javabank.ui.controller.customer.RemoveCustomerController;
import org.academiadecodigo.javabank.ui.controller.customer.ShowCustomerController;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu extends Menu {
    public MainMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    public void init() {
        super.init();

        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        menuMap.put(1, new AddNewCustomerController(-1, getPrompt(), getBank(), this));
        menuMap.put(2, new ShowCustomerController(-1, getPrompt(), getBank(), this));
        menuMap.put(3, new EditCustomerController(-1, getPrompt(), getBank(), this));
        menuMap.put(4, new RemoveCustomerController(-1, getPrompt(), getBank(), this));

        int option = createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();

        init();
    }
}
