package org.academiadecodigo.javabank.ui.view.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.javabank.ui.view.PromptView;

public class TransferMoneyPromptView extends PromptView {

    @Override
    public void success() {
        System.out.println("\nSuccessfully sent money");
    }

    @Override
    public void error() {
        System.out.println("\nError transferring money");
    }
}
