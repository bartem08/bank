package com.bank.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@MappedSuperclass
public class Operation implements Serializable {

    @Id @GeneratedValue
    protected Integer id;

    @Column(name = "saldo")
    protected float saldo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date")
    protected String date;

    @Column(name = "description")
    protected String description;

    @Column(name = "confirmed")
    protected boolean confirmed;

    public Operation() {}

    public Operation(float saldo, String date, String description) {
        setSaldo(saldo);
        setDate(date);
        setDescription(description);
        setConfirmed(false);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        return id != null ? id.equals(operation.id) : operation.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
