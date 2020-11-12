package org.academiadecodigo.javabank.controller.menu;

import org.academiadecodigo.javabank.controller.CustomerController;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.controller.AccountController;
import org.academiadecodigo.javabank.controller.account.*;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.view.PromptView;
import org.academiadecodigo.javabank.view.menu.EditCustomerMenuView;
import org.academiadecodigo.javabank.view.menu.MainMenuView;
import org.academiadecodigo.javabank.ui.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditCustomerMenuController extends CustomerController {

    private final EditCustomerMenuView view;

    public EditCustomerMenuController(CustomerService customerService, AccountService accountService, AuthenticationService authenticationService) {
        super(customerService, accountService, authenticationService);

        view = new EditCustomerMenuView();
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

        Map<Integer, AccountController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        menuMap.put(1, new AddAccountController(getAccountService(), getAuthenticationService()));
        menuMap.put(2, new CloseAccountController(getAccountService(), getAuthenticationService()));
        menuMap.put(3, new ShowAccountController(getAccountService(), getAuthenticationService()));
        menuMap.put(4, new TransferMoneyController(getAccountService(), getAuthenticationService(), getCustomerService()));
        menuMap.put(5, new DepositMoneyController(getAccountService(), getAuthenticationService()));
        menuMap.put(6, new WithdrawMoneyController(getAccountService(), getAuthenticationService()));

        int option = view.createMenu(menuItems);
        if (option == menuItems.length) {
            PromptView menuView = new MainMenuView();
            menuView.execute();
            getAuthenticationService().setCustomerService(null);
            return;
        }

        menuMap.get(option).execute();

        execute();
    }
}
