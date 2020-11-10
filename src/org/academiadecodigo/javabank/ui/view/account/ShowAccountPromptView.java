package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class ShowAccountPromptView extends PromptView {

    private final String allAccountsInfo;

    public ShowAccountPromptView(String allAccountsInfo) {
        this.allAccountsInfo = allAccountsInfo;
    }

    @Override
    public void success() {
        System.out.println("\n" + allAccountsInfo);
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }
}
