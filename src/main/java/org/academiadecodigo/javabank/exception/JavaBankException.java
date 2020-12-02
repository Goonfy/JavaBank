package org.academiadecodigo.javabank.exception;

public abstract class JavaBankException extends RuntimeException {

    public JavaBankException(String message) {
        super(message);
    }
}
