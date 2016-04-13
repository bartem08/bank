package com.bank.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id @GeneratedValue
    private Integer id;

    @NotNull @Size(min = 2, max = 50)
    @Column(name = "first_name",
            nullable = false,
            length = 50)
    private String firstName;

    @NotNull @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotNull @Email
    @Column(name = "mail")
    private String mail;

    @NotNull @Pattern(regexp = "^\\d{10}$")
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "inn")
    private String inn;

    @NotNull @Size(min = 4)
    @Column(name = "psw")
    private String password;

    @Column(name = "enabled")
    protected Boolean enabled;

    public User() {}

    public User(String firstName, String lastName, String mail, String inn, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setMail(mail);
        setInn(inn);
        setPhone(phone);
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return inn != null ? inn.equals(user.inn) : user.inn == null;

    }

    @Override
    public int hashCode() {
        return inn != null ? inn.hashCode() : 0;
    }
    
}
