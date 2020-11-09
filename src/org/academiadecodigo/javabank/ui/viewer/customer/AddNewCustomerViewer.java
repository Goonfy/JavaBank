package org.academiadecodigo.javabank.ui.viewer.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class AddNewCustomerViewer extends Viewer {

    public AddNewCustomerViewer(Prompt prompt, Menu menu) {
        super(prompt, menu);
    }

    @Override
    public void success() {
        System.out.println("\nAdded new customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't add new customer");
    }

    public String getName() {
        StringInputScanner questionName = new StringInputScanner();
        questionName.setMessage("Type in a name: ");
        return getPrompt().getUserInput(questionName);
    }

    public String getEmail() {
        StringInputScanner questionEmail = new StringInputScanner();
        questionEmail.setMessage("Type in an email: ");
        return getPrompt().getUserInput(questionEmail);
    }

    public String getPhone() {
        StringInputScanner questionPhoneNumber = new StringInputScanner();
        questionPhoneNumber.setMessage("Type in a Phone Number: ");
        return getPrompt().getUserInput(questionPhoneNumber);
    }
}
