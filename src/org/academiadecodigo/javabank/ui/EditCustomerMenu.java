package org.academiadecodigo.javabank.ui;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;

public class EditCustomerMenu extends Menu {

    public EditCustomerMenu(Prompt prompt, Bank bank) {
        super(prompt, bank);
    }

    @Override
    public void init() {
        createMenu(new MenuItem[]{MenuItem.ADDACCOUNT, MenuItem.CLOSEACCOUNTS,
                MenuItem.SHOWACCOUNTS, MenuItem.TRANSFERMONEY, MenuItem.DEPOSITMONEY, MenuItem.WITHDRAWMONEY});

        super.init();
    }

    private void addNewCustomer() {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("Type in a name: ");

        StringInputScanner questionEmail = new StringInputScanner();
        questionEmail.setMessage("Type in an email: ");

        StringInputScanner questionPhoneNumber = new StringInputScanner();
        questionPhoneNumber.setMessage("Type in a Phone Number: ");

        String name = getPrompt().getUserInput(questionName);
        String email = getPrompt().getUserInput(questionEmail);
        String phoneNumber = getPrompt().getUserInput(questionPhoneNumber);

        getMenuHandler().addNewCustomer(name, email, phoneNumber);
    }

    private void removeCustomer() {
        getMenuHandler().removeCustomer(createCustomerMenu());
    }

    private void addAccount() {
        getMenuHandler().addAccount(createAccountMenu(createCustomerMenu()));
    }
}
