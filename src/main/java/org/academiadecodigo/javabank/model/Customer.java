package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The customer domain entity
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "customer",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<AbstractAccount> accounts = new ArrayList<>();

    public Customer() {

    }

    public Customer(int id, String profilePicUrl, String firstName, String lastName, String email, String phoneNumber, List<AbstractAccount> accounts) {
        this.id = id;
        this.profilePicUrl = profilePicUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    /*public void addAccount(AbstractAccount account) {
        accounts.add(account);
    }

    public void removeAccount(AbstractAccount account) {
        accounts.remove(account);
    }*/

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<AbstractAccount> getAccounts() {
        return accounts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccounts(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public String toString() {
        return id + " - " + firstName + " " + lastName;
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
