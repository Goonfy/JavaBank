package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;

public abstract class AccountController implements Controller {

    private final JpaAccountService accountService;
    private final JpaAuthenticationService authenticationService;

    public AccountController(JpaAccountService accountService, JpaAuthenticationService authenticationService) {
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    public JpaAccountService getAccountService() {
        return accountService;
    }

    public JpaAuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
