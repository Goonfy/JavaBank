package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class DepositMoneyViewer extends Viewer {
    public DepositMoneyViewer(Prompt prompt) {
        super(prompt);
    }

    @Override
    public void success() {

    }

    @Override
    public void error() {

    }

    @Override
    public int getInput() {
        return 0;
    }
}
