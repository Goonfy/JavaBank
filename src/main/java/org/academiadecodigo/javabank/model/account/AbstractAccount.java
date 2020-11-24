package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.*;
import java.util.Objects;

/**
 * A generic account domain entity to be used as a base for concrete types of accounts
 */
@Entity
@Table(name = "accounts")
public abstract class AbstractAccount implements Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double balance;

    @ManyToOne
    private Customer customer;

    @Override
    public void addBalance(double amount) {
        balance += amount;
    }

    @Override
    public void removeBalance(double amount) {
        balance -= amount;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public boolean canCredit(double amount) {
        return amount > 0;
    }

    @Override
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= getBalance();
    }

    @Override
    public boolean canWithdraw() {
        return getAccountType() == AccountType.CHECKING;
    }

    @Override
    public String toString() {
        return "\n" + id + " - [ Balance: " + balance + ", Type: " + getAccountType() + " ]";
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof AbstractAccount && id == ((AbstractAccount) object).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
