package org.academiadecodigo.javabank.ui.viewer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.MenuItem;

public abstract class Viewer {

    private final Prompt prompt;
    private final Menu menu;

    public Viewer(Prompt prompt, Menu menu) {
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
