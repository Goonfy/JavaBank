package org.academiadecodigo.javabank.rest;

import org.academiadecodigo.javabank.controller.dto.AccountDto;
import org.academiadecodigo.javabank.controller.dto.CustomerDto;
import org.academiadecodigo.javabank.controller.dto.DtoMapper;
import org.academiadecodigo.javabank.exception.InvalidAccountID;
import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.service.AccountService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RestJavaBankController {

    private final CustomerService customerService;
    private final AccountService accountService;

    @Autowired
    public RestJavaBankController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> listCustomers() {
        return new ResponseEntity<>(DtoMapper.convertCustomerListToDto(customerService.listAll()), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/account")
    public ResponseEntity<List<AccountDto>> listAccounts(@PathVariable Integer id) {
        return new ResponseEntity<>(DtoMapper.convertAccountListToDto(customerService.get(id).getAccounts()), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(DtoMapper.convertToDto(customerService.get(id)), HttpStatus.OK);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{cid}/account/{aid}")
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

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto, BindingResult validation) {
        if (validation.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Customer customer = customerService.get(id);
            customer.setProfilePicUrl(customerDto.getProfilePicUrl());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customer.setAccounts(customerDto.getAccounts());

            customerService.add(customer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{cid}/account/{aid}")
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

    @PostMapping("/customer")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerDto, BindingResult validation, UriComponentsBuilder componentsBuilder) {
        if (validation.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //try {
        customerService.add(DtoMapper.convertFromDto(customerDto));

        UriComponents component = componentsBuilder
                .path("/api/gnuna/" + customerDto.getId())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(component.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

        //} catch (MalformedGnunaException e) {
        //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //}
    }
}
