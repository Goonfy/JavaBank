package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The customer domain entity
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private int id;

    private final String name;
    private final String email;
    private final String phoneNumber;

    private final Set<Integer> accountsId;

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        accountsId = new HashSet<>();
    }

    public void addAccount(int id) {
        accountsId.add(id);
    }

    public void removeAccount(int id) {
        accountsId.remove(id);
    }

    public String getAllAccountsInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int id : accountsId) {
            stringBuilder.append(id);
        }

        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "\n" + id + " - [ Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber + " ]";
    }
}
