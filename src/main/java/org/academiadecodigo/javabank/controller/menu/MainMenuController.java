package org.academiadecodigo.javabank.controller.menu;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.customer.AddNewCustomerController;
import org.academiadecodigo.javabank.controller.customer.RemoveCustomerController;
import org.academiadecodigo.javabank.controller.customer.ShowCustomerController;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.ui.MenuItem;
import org.academiadecodigo.javabank.view.menu.MainMenuView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenuController extends CustomerController {

    private final MainMenuView view;
    private Map<Integer, CustomerController> menuMap;

    public MainMenuController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

        this.view = new MainMenuView();
    }

    @Override
    public void execute() {
        view.success();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        int option = view.createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();

        execute();
    }

    public void setMenuMap(Map<Integer, CustomerController> menuMap) {
        this.menuMap = menuMap;
    }
}
