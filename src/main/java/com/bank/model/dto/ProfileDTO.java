package com.bank.model.dto;

import com.bank.model.Profile;

public class ProfileDTO {

    private String firstName;

    private String lastName;

    public ProfileDTO(final Profile profile) {
        setFirstName(profile.getFirstName());
        setLastName(profile.getLastName());
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
