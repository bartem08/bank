package com.bank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transfers")
public class Transfer extends Operation implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account", referencedColumnName = "id")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account", referencedColumnName = "id")
    private Account toAccount;

    public Transfer() {}

    public Transfer(final Account fromAccount, final Account toAccount,
                    final Date date, final float saldo, final String description) {
        super(saldo, new SimpleDateFormat("dd/MM/yyyy").format(date), description);
        setFromAccount(fromAccount);
        setToAccount(toAccount);
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
