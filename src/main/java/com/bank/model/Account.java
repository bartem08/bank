package com.bank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_inn", referencedColumnName = "inn")
    private Profile profile;

    @Column(name = "balance")
    private float balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccount")
    private List<Deposit> deposits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccount")
    private List<Transfer> inTransfers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAccount")
    private List<Transfer> outTransfers;

    public Account() {
    }

    public Account(final Profile profile) {
        setProfile(profile);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Transfer> getInTransfers() {
        return inTransfers;
    }

    public void setInTransfers(List<Transfer> inTransfers) {
        this.inTransfers = inTransfers;
    }

    public List<Transfer> getOutTransfers() {
        return outTransfers;
    }

    public void setOutTransfers(List<Transfer> outTransfers) {
        this.outTransfers = outTransfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id != null ? id.equals(account.id) : account.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
