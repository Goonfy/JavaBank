package org.academiadecodigo.javabank.ui.operations.account;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.ui.Menu;
import org.academiadecodigo.javabank.ui.operations.Operation;

public class WithdrawMoney extends Operation {
    public WithdrawMoney(int customerId, Prompt prompt, Bank bank, Menu menu) {
        super(customerId, prompt, bank, menu);
    }

    @Override
    public void execute() {

    }
}
