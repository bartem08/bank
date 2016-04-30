package com.bank.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class Profile implements Serializable {

    @Id @GeneratedValue @Null
    private Integer id;

    @NotNull @Size(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotNull @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "inn")
    private String inn;

    @NotNull @Size(min = 4)
    @Column(name = "password")
    private String password;

    @NotNull @Email
    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "profile")
    private List<Account> accounts;

    public Profile() {}

    public Profile(String firstName, String lastName, String inn, String password, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setInn(inn);
        setEmail(email);
        setPassword(password);
        setEnabled(true);
    }

    public Profile(String firstName, String lastName, String inn, String password, List<Role> roles, List<Account> accounts, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setInn(inn);
        setEmail(email);
        setPassword(password);
        setRoles(roles);
        setAccounts(accounts);
        setEnabled(true);
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return inn != null ? inn.equals(profile.inn) : profile.inn == null;

    }

    @Override
    public int hashCode() {
        return inn != null ? inn.hashCode() : 0;
    }

}
