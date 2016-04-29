package com.bank.model.dto;

import com.bank.model.Account;

public class AccountDTO {

    private int id;

    private String profile;

    private float balance;

    public AccountDTO(Account account) {
        setProfile(account.getProfile().getFirstName() + " " + account.getProfile().getLastName());
        setId(account.getId());
        setBalance(account.getBalance());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
