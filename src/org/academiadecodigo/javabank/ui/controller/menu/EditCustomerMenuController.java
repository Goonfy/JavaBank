package org.academiadecodigo.javabank.ui.controller.menu;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.controller.account.*;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.*;
import org.academiadecodigo.javabank.ui.view.menu.MainMenuView;
import org.academiadecodigo.javabank.ui.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditCustomerMenuController extends OperationController {

    public EditCustomerMenuController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {

        int chosenCostumer = getView().createCustomerMenu(getBank());
        if (chosenCostumer == -1) {
            getView().error();
            return;
        }

        getView().success();

        Map<Integer, OperationController> menuMap = new LinkedHashMap<>();

        MenuItem[] menuItems = new MenuItem[]{ MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS, MenuItem.SHOWACCOUNTS,
                MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY, MenuItem.BACK };

        menuMap.put(1, new AddAccountController(getBank(), new AddAccountPromptView(),
                getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(2, new CloseAccountController(getBank(), new CloseAccountPromptView(),
                getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(3, new ShowAccountController(getBank(), new ShowAccountPromptView(getBank()
                .getCustomerFromID(chosenCostumer).getAccountManager().getAllAccountsInfo()),
                getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(4, new TransferMoneyController(getBank(), new TransferMoneyPromptView(),
                getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(5, new DepositMoneyController(getBank(), new DepositMoneyPromptView(),
                getBank().getCustomerFromID(chosenCostumer)));
        menuMap.put(6, new WithdrawMoneyController(getBank(), new WithdrawMoneyPromptView(),
                getBank().getCustomerFromID(chosenCostumer)));

        int option = getView().createMenu(menuItems);
        if (option == menuItems.length) {
            PromptView menuView = new MainMenuView();
            menuView.execute();
            return;
        }

        menuMap.get(option).execute();

        execute();
    }
}
