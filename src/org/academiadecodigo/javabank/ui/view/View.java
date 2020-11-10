package org.academiadecodigo.javabank.ui.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.Menu;

public abstract class View {

    private final Prompt prompt;
    private final Menu menu;

    //TODO change name to View
    public View(Prompt prompt, Menu menu) {
        this.prompt = prompt;
        this.menu = menu;
    }

    public abstract void success();
    public abstract void error();

    public Prompt getPrompt() {
        return prompt;
    }

    public Menu getMenu() {
        return menu;
    }
}
