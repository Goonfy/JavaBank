package org.academiadecodigo.javabank.service;

import org.academiadecodigo.javabank.domain.Customer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The bank entity
 */
public class CustomerService implements CustomerServiceInterface {

    private int numberOfCustomers;

    private final Set<Customer> customers = new LinkedHashSet<>();

    @Override
    public void add(Customer customer) {
        if (!customers.add(customer)) {
            return;
        }

        numberOfCustomers++;

        customer.setId(numberOfCustomers);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public double getBalance(int customerId) {

        double balance = 0;

        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                balance = customer.getBalance();
                break;
            }
        }

        return balance;
    }

    @Override
    public Set<Customer> list() {
        return customers;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        Set<Integer> set = new LinkedHashSet<>();

        for (Customer customer : customers) {
            set.add(customer.getId());
        }

        return set;
    }

    public Customer get(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }

    public String getAllCustomersInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Customer customer : customers) {
            stringBuilder.append(customer.toString());
        }

        return stringBuilder.toString();
    }

    public int getNumberOfCustomers() {
        return customers.size();
    }
}
