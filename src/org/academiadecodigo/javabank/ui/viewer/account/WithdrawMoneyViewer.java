package org.academiadecodigo.javabank.ui.viewer.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.ui.viewer.Viewer;

public class WithdrawMoneyViewer extends Viewer {

    public WithdrawMoneyViewer(Prompt prompt) {
        super(prompt);
    }

    @Override
    public int getInput() {
        return 0;
    }
}
