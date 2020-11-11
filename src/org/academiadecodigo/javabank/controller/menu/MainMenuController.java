package org.academiadecodigo.javabank.controller.menu;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.customer.AddNewCustomerController;
import org.academiadecodigo.javabank.controller.customer.RemoveCustomerController;
import org.academiadecodigo.javabank.controller.customer.ShowCustomerController;
import org.academiadecodigo.javabank.ui.MenuItem;
import org.academiadecodigo.javabank.view.menu.MainMenuView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenuController extends CustomerController {

    private final MainMenuView view;

    public MainMenuController(AccountService customerServiceImplementation) {
        super(customerServiceImplementation);

        this.view = new MainMenuView();
    }

    @Override
    public void execute() {
        view.success();

        Map<Integer, CustomerController> menuMap = new LinkedHashMap<>();

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
