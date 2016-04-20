package com.bank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
public class Role implements Serializable {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_inn", referencedColumnName = "inn")
    private User user;

    public Role() {}

    public Role(User user, String role) {
        setUser(user);
        setRole(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}