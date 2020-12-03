package org.academiadecodigo.javabank.controller.dto;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setProfilePicUrl(customer.getProfilePicUrl());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());

        return customerDto;
    }

    public static Customer convertToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setProfilePicUrl(customerDto.getProfilePicUrl());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        return customer;
    }

    public static AccountDto convertToDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(accountDto.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setCustomer(account.getCustomer());

        return accountDto;
    }

    /*public static Account convertToAccount(AccountDto accountDto) {
        Account account = new AbstractAccount() {
        }
    }*/

    public static List<CustomerDto> convertToCustomerDtoList(List<Customer> customerList) {
        return customerList.stream().map(e -> new CustomerDto(e.getId(), e.getProfilePicUrl(), e.getFirstName(), e.getLastName(), e.getEmail(), e.getPhoneNumber(), e.getAccounts())).collect(Collectors.toList());
    }

    public static List<Customer> convertToCustomerList(List<CustomerDto> customerDtoList) {
        return customerDtoList.stream().map(e -> new Customer(e.getId(), e.getProfilePicUrl(), e.getFirstName(), e.getLastName(), e.getEmail(), e.getPhoneNumber(), e.getAccounts())).collect(Collectors.toList());
    }
}
