package org.academiadecodigo.javabank.ui;

public enum MenuItem {
    NEW("new"),
    SHOW("show"),
    EDIT("edit"),
    DELETE("delete");

    private final String menuOption;

    MenuItem(String menuOption) {
        this.menuOption = menuOption;
    }

    public String getMenuOption() {
        return menuOption;
    }
}
