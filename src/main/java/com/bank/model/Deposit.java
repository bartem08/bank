package com.bank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit extends Operation implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account", referencedColumnName = "id")
    private Account toAccount;

    @Column(name = "closed")
    private boolean closed;

    public Deposit() {}

    public Deposit(final Account toAccount, final Date date, final float saldo) {
        super(saldo, new SimpleDateFormat("dd/MM/yyyy").format(date), "Deposit");
        setToAccount(toAccount);
        setClosed(false);
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
