package org.academiadecodigo.javabank.view.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.view.PromptView;

public class EditCustomerMenuView extends PromptView {

    public EditCustomerMenuView(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {
        System.out.println("\nEdit Customer Menu");
    }

    @Override
    public void error() {
        System.out.println("Can't create this menu");
    }
}
