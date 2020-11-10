package org.academiadecodigo.javabank.ui.controller.menu;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.account.*;
import org.academiadecodigo.javabank.ui.view.MenuView;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.View;
import org.academiadecodigo.javabank.ui.view.account.*;
import org.academiadecodigo.javabank.ui.view.menu.MainMenuView;
import org.academiadecodigo.javabank.ui.view.menu.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditCustomerMenuController extends OperationController {

    public EditCustomerMenuController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        int customerId = getView().createCustomerMenu();
        if (customerId == -1) {
            return;
        }

        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        menuMap.put(1, new AddAccountController(getBank(), new AddAccountPromptView()));
        menuMap.put(2, new CloseAccountController(getBank(), new CloseAccountPromptView()));
        menuMap.put(3, new ShowAccountController(getBank(), new ShowAccountPromptView()));
        menuMap.put(4, new TransferMoneyController(getBank(), new TransferMoneyPromptView()));
        menuMap.put(5, new DepositMoneyController(getBank(), new DepositMoneyPromptView()));
        menuMap.put(6, new WithdrawMoneyController(getBank(), new WithdrawMoneyPromptView()));

        int option = getView().createMenu(menuItems);
        if (option == menuItems.length) {
            MenuView menuView = new MainMenuView();
            menuView.execute();
            return;
        }

        menuMap.get(option).execute();
    }
}
