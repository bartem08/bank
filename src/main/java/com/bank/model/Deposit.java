package com.bank.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "deposits")
public class Deposit extends Operation implements Serializable {

    @ManyToOne
    @JoinColumn(name = "to_account", referencedColumnName = "id")
    private Account toAccount;

    public Deposit(Account toAccount) {
        this.toAccount = toAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

}
