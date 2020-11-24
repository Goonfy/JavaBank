package org.academiadecodigo.javabank.controller.menu;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.controller.account.*;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
import org.academiadecodigo.javabank.view.PromptView;
import org.academiadecodigo.javabank.view.menu.EditCustomerMenuView;
import org.academiadecodigo.javabank.view.menu.MainMenuView;
import org.academiadecodigo.javabank.ui.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditCustomerMenuController extends CustomerController {

    private EditCustomerMenuView view;
    private Map<Integer, AccountController> menuMap;

    public EditCustomerMenuController(JpaCustomerService customerService, JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);
    }

    @Override
    public void execute() {
        if (getAuthenticationService().getAccessingCustomer() == null) {
            getAuthenticationService().setCustomerService(getCustomerService());

            int chosenCostumer = view.createCustomerMenu(getCustomerService());

            if (!getAuthenticationService().authenticate(chosenCostumer)) {
                view.error();
                return;
            }
        }

        view.success();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        int option = view.createMenu(menuItems);
        menuMap.get(option).execute();

        execute();
    }

    public void setMenuMap(Map<Integer, AccountController> menuMap) {
        this.menuMap = menuMap;
    }

    public void setView(EditCustomerMenuView view) {
        this.view = view;
    }
}
