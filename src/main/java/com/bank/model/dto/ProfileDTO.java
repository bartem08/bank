package com.bank.model.dto;

import com.bank.model.Profile;

public class ProfileDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    public ProfileDTO(final Profile profile) {
        setId(profile.getId());
        setFirstName(profile.getFirstName());
        setLastName(profile.getLastName());
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

}
