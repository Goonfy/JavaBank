package org.academiadecodigo.javabank.controller.menu;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.customer.AddNewCustomerController;
import org.academiadecodigo.javabank.controller.customer.RemoveCustomerController;
import org.academiadecodigo.javabank.controller.customer.ShowCustomerController;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.ui.MenuItem;
import org.academiadecodigo.javabank.view.menu.MainMenuView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenuController extends CustomerController {

    private final MainMenuView view;

    public MainMenuController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

        this.view = new MainMenuView();
    }

    @Override
    public void execute() {
        view.success();

        Map<Integer, CustomerController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{MenuItem.NEWCUSTOMER, MenuItem.SHOWCUSTOMERS,
                MenuItem.EDITCUSTOMERS, MenuItem.REMOVECUSTOMERS, MenuItem.EXIT};

        menuMap.put(1, new AddNewCustomerController(getCustomerService(), getAccountService(), getAuthenticationService()));
        menuMap.put(2, new ShowCustomerController(getCustomerService(), getAccountService(), getAuthenticationService()));
        menuMap.put(3, new EditCustomerMenuController(getCustomerService(), getAccountService(), getAuthenticationService()));
        menuMap.put(4, new RemoveCustomerController(getCustomerService(), getAccountService(), getAuthenticationService()));

        int option = view.createMenu(menuItems);
        if (option == menuItems.length) {
            System.exit(0);
        }

        menuMap.get(option).execute();

        execute();
    }
}
