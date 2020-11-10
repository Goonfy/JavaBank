package org.academiadecodigo.javabank.ui.controller.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.EditCustomerMenu;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.controller.OperationController;

public class EditCustomerController extends OperationController {

    public EditCustomerController(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Menu menu = new EditCustomerMenu(getPrompt(), getBank());
        menu.init();
    }
}
