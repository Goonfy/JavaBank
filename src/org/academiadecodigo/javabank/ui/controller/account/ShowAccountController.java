package org.academiadecodigo.javabank.ui.controller.account;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.ui.controller.OperationController;
import org.academiadecodigo.javabank.ui.view.PromptView;
import org.academiadecodigo.javabank.ui.view.account.ShowAccountPromptView;

public class ShowAccountController extends OperationController {
    public ShowAccountController(Bank bank, PromptView view) {
        super(bank, view);
    }

    @Override
    public void execute() {
        PromptView promptView = new ShowAccountPromptView(getCustomerId(), getPrompt(), getMenu(), getBank());

        Customer customer = getBank().getCustomerFromID(getCustomerId());
        if (customer.getAllAccountsInfo().isEmpty()) {
            promptView.error();
        }

        promptView.success();
    }
}
