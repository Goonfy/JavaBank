package org.academiadecodigo.javabank.controller.dto;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.*;
import org.academiadecodigo.javabank.service.CustomerService;

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

    public static Customer convertFromDto(CustomerDto customerDto) {
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

        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setCustomerId(account.getCustomer().getId());

        return accountDto;
    }

    public static AbstractAccount convertFromDto(AccountDto accountDto) {
        return accountDto.getAccountType() == AccountType.CHECKING ? new CheckingAccount(accountDto.getId(), accountDto.getBalance()) : new SavingsAccount(accountDto.getId(), accountDto.getBalance());
    }

    public static List<CustomerDto> convertCustomerListToDto(List<Customer> customerList) {
        return customerList.stream().map(DtoMapper::convertToDto).collect(Collectors.toList());
    }

    public static List<Customer> convertFromCustomerDtoList(List<CustomerDto> customerDtoList) {
        return customerDtoList.stream().map(DtoMapper::convertFromDto).collect(Collectors.toList());
    }

    public static List<AccountDto> convertAccountListToDto(List<AbstractAccount> accountList) {
        return accountList.stream().map(DtoMapper::convertToDto).collect(Collectors.toList());
    }

    public static List<AbstractAccount> convertFromAccountDtoList(List<AccountDto> accountDtoList) {
        return accountDtoList.stream().map(DtoMapper::convertFromDto).collect(Collectors.toList());
    }
}
