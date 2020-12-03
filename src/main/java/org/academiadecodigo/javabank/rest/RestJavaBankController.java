package org.academiadecodigo.javabank.rest;

import org.academiadecodigo.javabank.controller.dto.CustomerDto;
import org.academiadecodigo.javabank.controller.dto.DtoMapper;
import org.academiadecodigo.javabank.exception.InvalidCustomerID;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.CustomerService;
import org.academiadecodigo.javabank.service.JpaCustomerService;
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

@RestController
@RequestMapping("/api")
public class RestJavaBankController {

    private final CustomerService customerService;

    @Autowired
    public RestJavaBankController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> list() {
        return new ResponseEntity<>(DtoMapper.convertToCustomerDtoList(customerService.listAll()), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(DtoMapper.convertToDto(customerService.get(id)), HttpStatus.OK);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customer/edit/{id}")
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

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            customerService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (InvalidCustomerID e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/create")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerDto, BindingResult validation, UriComponentsBuilder componentsBuilder) {
        if (validation.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //try {
        customerService.add(DtoMapper.convertToCustomer(customerDto));

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
