package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.domain.account.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The customer domain entity
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private final String name;
    private final String email;
    @Column(name = "phone_number")
    private final String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private final Set<Account> accounts;

    public Customer() {
        this("", "", "");
    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        accounts = new HashSet<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public String getAllAccountsInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Account account : accounts) {
            stringBuilder.append(account.toString());
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
