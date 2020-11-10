package org.academiadecodigo.javabank.ui.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.controller.Controller;
import org.academiadecodigo.javabank.ui.view.PromptView;

public abstract class MenuView extends PromptView {

    public void execute() {
        try {
            System.out.println("\nPlease wait...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
