package org.academiadecodigo.javabank.ui.controller.menu;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.customer.AddNewCustomerController;
import org.academiadecodigo.javabank.ui.controller.customer.RemoveCustomerController;
import org.academiadecodigo.javabank.ui.controller.customer.ShowCustomerController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.customer.AddNewCustomerPromptView;
import org.academiadecodigo.javabank.ui.view.customer.RemoveCustomerPromptView;
import org.academiadecodigo.javabank.ui.view.customer.ShowCustomerPromptView;
import org.academiadecodigo.javabank.ui.view.menu.EditCustomerMenuView;
import org.academiadecodigo.javabank.ui.view.menu.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenuController extends OperationController {

    public MainMenuController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        menuMap.put(1, new AddNewCustomerController(getBank(), new AddNewCustomerPromptView()));
        menuMap.put(2, new ShowCustomerController(getBank(), new ShowCustomerPromptView()));
        menuMap.put(3, new EditCustomerMenuController(getBank(), new EditCustomerMenuView()));
        menuMap.put(4, new RemoveCustomerController(getBank(), new RemoveCustomerPromptView()));

        int option = getView().createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();
    }
}
