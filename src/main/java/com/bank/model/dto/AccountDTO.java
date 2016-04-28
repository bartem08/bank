package com.bank.model.dto;

import com.bank.model.Account;

public class AccountDTO {

    private int id;

    private float balance;

    public AccountDTO(Account account) {
        setId(account.getId());
        setBalance(account.getBalance());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
