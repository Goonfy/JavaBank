package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class ShowAccountPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\n" );
    }

    @Override
    public void error() {
        System.out.println("\nPlease create a costumer before proceeding...");
    }
}
