package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.AbstractAccount;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    private final List<AbstractAccount> accounts;

    public Customer() {
        this("", "", "");
    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        accounts = new ArrayList<>();
    }

    public void addAccount(AbstractAccount account) {
        accounts.add(account);
    }

    public void removeAccount(AbstractAccount account) {
        accounts.remove(account);
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

    @Override
    public boolean equals(Object object) {
        return object instanceof Customer && id == ((Customer) object).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}