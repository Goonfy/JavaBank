package org.academiadecodigo.javabank.rest;

import org.academiadecodigo.javabank.controller.dto.AccountDto;
import org.academiadecodigo.javabank.controller.dto.DtoMapper;
import org.academiadecodigo.javabank.exception.InvalidAccountID;
import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/accounts")
public class RestJavaBankAccountController {

    private final CustomerService customerService;
    private final AccountService accountService;

    @Autowired
    public RestJavaBankAccountController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> listAccounts() {
        return new ResponseEntity<>(DtoMapper.convertAccountListToDto(accountService.listAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}/account")
    public ResponseEntity<List<AccountDto>> listUserAccounts(@PathVariable Integer id) {
        return new ResponseEntity<>(DtoMapper.convertAccountListToDto(customerService.get(id).getAccounts()), HttpStatus.OK);
    }

    @GetMapping("/{cid}/account/{aid}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Integer cid, @PathVariable Integer aid) {
        try {
            if (!customerService.get(cid).getAccounts().contains(accountService.get(aid))) {
                throw new InvalidAccountID();
            }

            return new ResponseEntity<>(DtoMapper.convertToDto(accountService.get(aid)), HttpStatus.OK);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cid}/account/{aid}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer cid, @PathVariable Integer aid) {
        try {
            if (!customerService.get(cid).getAccounts().contains(accountService.get(aid))) {
                throw new InvalidAccountID();
            }

            accountService.remove(aid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
