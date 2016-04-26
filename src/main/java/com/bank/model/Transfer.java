package com.bank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transfers")
public class Transfer extends Operation implements Serializable {

    @ManyToOne
    @JoinColumn(name = "from_account", referencedColumnName = "id")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account", referencedColumnName = "id")
    private Account toAccount;

    public Transfer() {}

    public Transfer(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }


}
