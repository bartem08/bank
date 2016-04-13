package com.bank.model.dto;

import com.bank.model.User;

public class UserDTO {

    private String firstName;

    private String lastName;

    public UserDTO(final User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
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

}
