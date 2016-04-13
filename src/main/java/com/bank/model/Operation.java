package com.bank.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "operations")
public class Operation {

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotNull @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "saldo")
    private float saldo;

    @Column(name = "description")
    private String description;

    public Operation() {}

    public Operation(Account account, String type, float saldo, String date, String description) {
        setAccount(account);
        setType(type);
        setSaldo(saldo);
        setDate(date);
        setDescription(description);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
