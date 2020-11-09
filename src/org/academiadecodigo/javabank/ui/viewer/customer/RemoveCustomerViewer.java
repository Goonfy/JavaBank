package org.academiadecodigo.javabank.ui.viewer.customer;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class RemoveCustomerViewer extends Viewer {

    public RemoveCustomerViewer(Prompt prompt, Menu menu) {
        super(prompt, menu);
    }

    @Override
    public void success() {
        System.out.println("\nRemoved customer successfully");
    }

    @Override
    public void error() {
        System.out.println("\nCan't remove customer");
    }

    public int getInput() {
        return getMenu().createCustomerMenu();
    }
}
