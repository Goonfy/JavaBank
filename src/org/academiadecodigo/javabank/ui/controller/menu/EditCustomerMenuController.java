package org.academiadecodigo.javabank.ui.controller.menu;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.account.*;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.*;
import org.academiadecodigo.javabank.ui.view.menu.EditCustomerMenuView;
import org.academiadecodigo.javabank.ui.view.menu.MainMenuView;
import org.academiadecodigo.javabank.ui.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditCustomerMenuController extends OperationController {

    private final EditCustomerMenuView view;

    public EditCustomerMenuController(Bank bank) {
        super(bank);

        view = new EditCustomerMenuView();
    }

    @Override
    public void execute() {

        int chosenCostumer = view.createCustomerMenu(getBank());
        if (chosenCostumer == -1) {
            view.error();
            return;
        }

        view.success();

        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        menuMap.put(1, new AddAccountController(getBank(), getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(2, new CloseAccountController(getBank(), getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(3, new ShowAccountController(getBank(), getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(4, new TransferMoneyController(getBank(), getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(5, new DepositMoneyController(getBank(), getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(6, new WithdrawMoneyController(getBank(), getBank().getCustomerFromID(chosenCostumer)));

        int option = view.createMenu(menuItems);
        if (option == menuItems.length) {
            PromptView menuView = new MainMenuView();
            menuView.execute();
            return;
        }

        menuMap.get(option).execute();

        execute();
    }
}
