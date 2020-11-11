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
import org.academiadecodigo.javabank.ui.MenuItem;
import org.academiadecodigo.javabank.ui.view.menu.MainMenuView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenuController extends OperationController {

    private final MainMenuView view;

    public MainMenuController(Bank bank) {
        super(bank);

        this.view = new MainMenuView();
    }

    @Override
    public void execute() {
        view.success();

        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        menuMap.put(1, new AddNewCustomerController(getBank()));
        menuMap.put(2, new ShowCustomerController(getBank()));
        menuMap.put(3, new EditCustomerMenuController(getBank()));
        menuMap.put(4, new RemoveCustomerController(getBank()));

        int option = view.createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();

        execute();
    }
}
