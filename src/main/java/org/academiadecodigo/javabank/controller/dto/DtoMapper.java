package org.academiadecodigo.javabank.controller.dto;

import org.academiadecodigo.javabank.model.Customer;

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
}
