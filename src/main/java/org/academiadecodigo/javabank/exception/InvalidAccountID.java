package org.academiadecodigo.javabank.exception;

public class InvalidAccountID extends JavaBankException {

    public InvalidAccountID() {
        super("Invalid customer ID.");
    }
}
