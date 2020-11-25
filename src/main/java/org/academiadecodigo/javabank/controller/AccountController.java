package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.JpaAccountService;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;

public abstract class AccountController implements Controller {

    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    public AccountController(AccountService accountService, AuthenticationService authenticationService) {
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
