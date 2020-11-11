package org.academiadecodigo.javabank.view.menu;

import org.academiadecodigo.javabank.view.PromptView;

public class MainMenuView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nMain Menu");
    }

    @Override
    public void error() {
        System.out.println("Can't create this menu");
    }
}
