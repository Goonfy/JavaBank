package org.academiadecodigo.javabank.ui.operations.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.EditCustomerMenu;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;

public class EditCustomer extends Operation {

    public EditCustomer(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {
        Menu menu = new EditCustomerMenu(getPrompt(), getBank());
        menu.init();
    }
}
