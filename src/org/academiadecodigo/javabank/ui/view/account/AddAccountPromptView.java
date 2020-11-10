package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class AddAccountPromptView extends PromptView {

    @Override
    public void success(String message) {
        System.out.println("\nAdded new account successfully");
    }

    @Override
    public void error(String message) {
        System.out.println("\nError adding new account");
    }

    public int getInput() {
        return createMenu(AccountType.values()) - 1;
    }
}
