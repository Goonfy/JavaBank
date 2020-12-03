package org.academiadecodigo.javabank.controller.dto;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.validation.constraints.*;
import java.util.List;

public class CustomerDto {

    private Integer id;

    private String profilePicUrl;

    @NotNull(message = "first name is mandatory")
    @NotBlank(message = "first name is mandatory")
    @Size(min = 3, max = 64)
    private String firstName;

    @NotNull(message = "last name is mandatory")
    @NotBlank(message = "last name is mandatory")
    @Size(min = 3, max = 64)
    private String lastName;

    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;

    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "phone has invalid characters")
    @Size(min = 9, max = 16)
    private String phoneNumber;

    private List<AbstractAccount> accounts;

    public CustomerDto() {

    }

    public CustomerDto(Integer id, String profilePicUrl, @NotBlank(message = "first name is mandatory") @Size(min = 3, max = 64) String firstName, @NotBlank(message = "last name is mandatory") @Size(min = 3, max = 64) String lastName, @NotBlank(message = "email is mandatory") String email, @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "phone has invalid characters") @Size(min = 9, max = 16) String phoneNumber, List<AbstractAccount> accounts) {
        this.id = id;
        this.profilePicUrl = profilePicUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public List<AbstractAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }
}
